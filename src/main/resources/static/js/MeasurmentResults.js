class Result{
    constructor(freq, value, error){
        this.freq = freq;
        this.value = value;
        this.error = error;
    }
}

class VSWRresult extends Result{
    constructor(freq, value, error, portNumber){
        super(freq, value, error);
        this.portNumber = portNumber;
    }
}

class DifferentialAttenuationResult extends Result{
    constructor(freq, value, error, startAttenuation, stopAttenuation){
        super(freq, value, error);
        this.startAttenuation = startAttenuation;
        this.stopAttenuation = stopAttenuation;
    }
}

class ResultWrapper{
    constructor(hashCode){
        this.hashCode = hashCode;
        this.results = new Array();
    }

    addResult(result){
        this.results.push(result);
    }
}