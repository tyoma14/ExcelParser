function sendExcel(){
    return new Promise(function(resolve, reject){
        let request = new XMLHttpRequest();
        let body = new FormData();
        let fileInput = document.getElementById("fileInput");
        let file = fileInput.files[0];
        let url = "/parseExcel?fileName=" + file.name;
        request.open("POST", url);
        let maxSize = 10485760;
        if(file.size > maxSize){
            reject(new Error("Файл весит слишком много"));
            return;
        }
        body.append("file", file);
        request.send(body);
        //
        //
        //слушаем ответ от сервера
        request.onloadend = () => resolve(request);
        request.onerror = () => reject(new Error("Потеря соединения с сервером"));
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
