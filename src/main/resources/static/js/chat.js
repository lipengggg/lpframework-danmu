var websocket=null;
var host=window.location.host;
var data = null;

//判断当前浏览器是否支持WebSocket
if('WebSocket' in window){
    websocket=new WebSocket("ws://"+host+"/webSocketHandler");
} else{
    alert("Not Support WebSocket!");
}

//连接发生错误的回调方法
websocket.onerror = function(){
    //setMessageInnerHTML("error");
};

//连接成功建立的回调方法
websocket.onopen = function(event){
    console.log(event);
    //setMessageInnerHTML("open");
}

//接收到消息的回调方法
// 收到服务器发送的消息
websocket.onmessage = function(){
    //setMessageInnerHTML(event.data);
    data = event.data;
}

//连接关闭的回调方法
websocket.onclose = function(){
    //setMessageInnerHTML("close");
}


//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
window.onbeforeunload = function(){
    websocket.close();
}

window.onload = function () {
    var user = ["../img/img.jpg"];
    var num = 1;//判断左右
    var portrait_position = 0;
    var now = -1;//左右浮动
    var send_btn = document.getElementById('send_btn');
    var send_txt = document.getElementById('send_txt');
    var chat_ul = document.getElementById('chat_ul');
    var chat_span = chat_ul.getElementsByTagName('span');
    var chat_img = chat_ul.getElementsByTagName('img');

    send_btn.onclick = function () {
        if (send_txt.value == '') {
            alert("请不要惜字如金");
        } else {
            websocket.send(send_txt.value);

            chat_ul.innerHTML += '<li><img src="../img/img.jpg"><span>' + data + '</span>';
            now++;
            if (num==0) {
                chat_span[now].className = 'spanright';
                chat_img[now].className = 'imgright';
            }
            else {
                chat_span[now].className = 'spanleft';
                chat_img[now].className = 'imgleft';
            }
            send_txt.value = '';
            // 内容过多时,将滚动条放置到最底端
            /*contentcontent.scrollTop = content.scrollHeight;*/
        }
    }


}