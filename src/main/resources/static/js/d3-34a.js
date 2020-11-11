var mainInfoIsSent = false, 
    vswrIsSent = false, 
    initAttIsSent = false,
    difAttIsSent = false;



function hideErrorFields(){
    if($("#errorRadio").prop("checked")){
        $("[id*='_err']").hide();
    }
    else{
        $("[id*='_err']").show();
    }
}

function sendMainData(){
    let mainData = new FormData();
    mainData.append("procedureHashCode", $("#procedureHashCode").val());       
    mainData.append("date", $("#date").val());

    let serial_number = $("#serial_number").val();
    if(serial_number === ""){
        alert("Вы не ввели серийный номер устройства");
        return;
    }
    mainData.append("serialNumber", serial_number);

    let temperature = parseFloat($("#temperature").val());
    if(isNaN(temperature)){
        alert("Вы ввели некорректное значение температуры");
        return;
    }
    mainData.append("temperature", temperature);

    let humidity = parseFloat($("#humidity").val());
    if(isNaN(humidity)){
        alert("Вы ввели некорректное значение влажности");
        return;
    }
    mainData.append("humidity", humidity);

    let preasure = parseFloat($("#preasure").val());
    if(isNaN(preasure)){
        alert("Вы ввели некорректное значение атмосферного давления");
        return;
    }
    mainData.append("preasure", preasure);

    $.ajax({type: "POST", url: "/attenuators/d3-34a/getMainData", cache: false, dataType: 'html', contentType: false, processData : false, async: false,  data: mainData,
        success: function(respond, status, jqXHR){
            $("#mainInfoAnswer").empty();
            $("#mainInfoAnswer").append(respond);
            mainInfoIsSent = true;
        }, 
        error: function(respond, status, jqXHR){
            alert('При отправке основной информации произошла ошибка: ' + status);
        }
    });
}

function sendVSWR(){
    let hashCode = $("#procedureHashCode").val();
    let vswrResultWrapper = new ResultWrapper(hashCode);
    let elementsIn = $("td[id^='vswr_in']");
    elementsIn.each(function(){
        let freq = $(this).children("[id^='vswr_in_freq']").val();
        let value = $(this).children("[id^='vswr_in_val']").val();
        let error = $(this).children("[id^='vswr_in_err']").val();
        let vswrRes = new VSWRresult(freq, value, error, 1);
        vswrResultWrapper.addResult(vswrRes);
    });
    let elementsOut = $("td[id^='vswr_out']");
    elementsOut.each(function(){
        let freq = $(this).children("[id^='vswr_out_freq']").val();
        let value = $(this).children("[id^='vswr_out_val']").val();
        let error = $(this).children("[id^='vswr_out_err']").val();
        let vswrRes = new VSWRresult(freq, value, error, 2);
        vswrResultWrapper.addResult(vswrRes);
    });
    let sData = JSON.stringify(vswrResultWrapper);
    $.ajax({type: "POST", url: "/attenuators/d3-34a/getVSWR", async: false, contentType: 'application/json', dataType:"html", data: sData,
        success: function(respond, status, jqXHR){
            $("#vswrAnswer").empty();
            $("#vswrAnswer").append(respond);
            vswrIsSent = true;
        }, 
        error: function(respond, status, jqXHR){
            alert('При загрузке произошла ошибка: ' + status);
        }
    });
}

function sendInitialAttenuation(){
    let hashCode = $("#procedureHashCode").val();
    let initAttResultWrapper = new ResultWrapper(hashCode);
    let elements = $("td[id^='init_att']");
    elements.each(function(){
        let freq = $(this).children("[id^='init_att_freq']").val();
        let value = $(this).children("[id^='init_att_val']").val();
        let error = $(this).children("[id^='init_att_err']").val();
        let vswrRes = new Result(freq, value, error);
        initAttResultWrapper.addResult(vswrRes);
    });
    let sData = JSON.stringify(initAttResultWrapper);
    $.ajax({type: "POST", url: "/attenuators/d3-34a/getInitialAttenuation", async: false, contentType: 'application/json', dataType:"html", data: sData,
        success: function(respond, status, jqXHR){
            $("#initialAttAnswer").empty();
            $("#initialAttAnswer").append(respond);
            initAttIsSent = true;
        }, 
        error: function(respond, status, jqXHR){
            alert('При загрузке произошла ошибка: ' + status);
        }
    });
}

function sendDifferentialAttenuation(){
    let hashCode = $("#procedureHashCode").val();
    let difAttenuationResultWrapper = new ResultWrapper(hashCode);
    let elementsGroup = $("tr[id^='dif_att_group']");
    elementsGroup.each(function(){
        var startAttenuation = $(this).children("td[id^='steps']").children("input[id^='startAtt']").val();
        var stopAttenuation = $(this).children("td[id^='steps']").children("input[id^='stopAtt']").val();
        let elements = $(this).children("td[id^='dif_att']");
        elements.each(function(){
            let freq = $(this).children("[id^='dif_att_freq']").val();
            let value = $(this).children("[id^='dif_att_val']").val();
            let error = $(this).children("[id^='dif_att_err']").val();
            let result = new DifferentialAttenuationResult(freq, value, error, startAttenuation, stopAttenuation);
            difAttenuationResultWrapper.addResult(result);
        });
    });
    let sData = JSON.stringify(difAttenuationResultWrapper);
    $.ajax({type: "POST", url: "/attenuators/d3-34a/getDifferentialAttenuation", async: false, contentType: 'application/json', dataType:"html", data: sData,
        success: function(respond, status, jqXHR){
            $("#difAttAnswer").empty();
            $("#difAttAnswer").append(respond);
            difAttIsSent = true;
        }, 
        error: function(respond, status, jqXHR){
            alert('При загрузке произошла ошибка: ' + status);
        }
    });
}

function sendAllData(){
    try{
        sendMainData();
    }
    catch{
        alert("Произошла ошибка при отправке основной информации");
    }

    try{
        sendVSWR();
    }
    catch{
        alert("Произошла ошибка при отправке КСВН");
    }

    try{
        sendInitialAttenuation();
    }
    catch{
        alert("Произошла ошибка при отправке начального ослабления");
    }

    try{
        sendDifferentialAttenuation();
    }
    catch{
        alert("Произошла ошибка при отправке разностного ослабления");
    }
}

function saveData(){
    if(!mainInfoIsSent){
        alert("Основная информация о поверке не отправлена");
        return;
    }
    if(!vswrIsSent){
        alert("Результаты измерения КСВН не отправлены");
        return;
    }
    if(!initAttIsSent){
        alert("Результаты измерения начального ослабления не отправлены");
        return;
    }
    if(!difAttIsSent){
        alert("Результаты измерения разностного ослабления не отправлены");
        return;
    }
    let savingData = new FormData();
    savingData.append("procedureHashCode", $("#procedureHashCode").val());

    $.ajax({type: "POST", url: "/attenuators/d3-34a/saveVerificationProcedure", cache: false, dataType: 'html', contentType: false, processData : false, async: false,  data: savingData,
        success: function(respond, status, jqXHR){
            $("#savingAnswer").empty();
            $("#savingAnswer").append(respond);
        }, 
        error: function(respond, status, jqXHR){
            alert('При загрузке произошла ошибка: ' + status);
        }
    });
}