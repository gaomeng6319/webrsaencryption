(function($w,app) {

    let  ajax=function(url,data,fn){
        var xhr=new XMLHttpRequest();
        xhr.open('POST',url,false);
        // 添加http头，发送信息至服务器时内容编码类型
        xhr.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
        xhr.onreadystatechange=function(){
            if (xhr.readyState==4){
                if (xhr.status==200 || xhr.status==304){
                    console.log(xhr.responseText);
                    fn(xhr.responseText);
                }
            }
        }
        xhr.send(data);
    }
    app.ajax=ajax;

    let myEncryptedString=function(data){

        RSAUtils.setMaxDigits(200);
        let key = new RSAUtils.getKeyPair(getInputValForId("publicKeyExponent"), "", getInputValForId("publicKeyModulus"));
        let encodeData = RSAUtils.encryptedString(key,data.split("").reverse().join(""));
        return  encodeData;


    }
    app.myEncryptedString=myEncryptedString;

    let getInputValForId=function (id) {
     return document.getElementById(id).value;
    }
    app.getInputValForId=getInputValForId;

})(window,app={});