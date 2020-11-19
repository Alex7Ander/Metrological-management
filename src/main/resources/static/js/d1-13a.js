var mainDataIsSent = false,
                difAttIsSent = false;

            function sendAllData(){
                try{
                    sendDifferentialAttenuation();
                }
                catch{
                    alert("Произошла ошибка при отправке разностного ослабления");
                }
            }

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

            function sendDifferentialAttenuation(){
                let hashCode = $("#procedureHashCode").val();
                let difAttenuationResultWrapper = new ResultWrapper(hashCode);
                let tables = $("table[id^='dif_att_table']");
                tables.each(function(){
                    let elementsGroup = $(this).children("tr[id^='dif_att_group']");
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