function sendExcel(){
    return new Promise(function(resolve, reject){
        let request = new XMLHttpRequest();
        let url = "/parseExcel";
        request.open("POST", url);
        let body = new FormData();
        let fileInput = document.getElementById("fileInput");
        let file = fileInput.files[0];
        body.append("file", file);
        body.append("fileName", file.name);
        request.send(body);
        //
        //
        //слушаем ответ от сервера
        request.onloadend = () => resolve(request);
        request.onerror = () => reject(new Error("Ошибка соединения"));
    });
}

function getParsedExcel(){
    let promise = sendExcel();
    promise.then(
        processResponse,
        error => alert(`Ошибка: ${error.message}`)
    );
}

function processResponse(request) {
    if(request.status == 200){
        let div = document.getElementById("contentDiv");
        div.innerHTML = "<pre>" + request.response + "</pre>";
    }
    else {
        alert(request.response);
    }
}
