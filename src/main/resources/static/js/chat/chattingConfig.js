import {Message} from "./chatMessage.js";

/**
 * 완성된 채팅 메세지만 넣으십쇼
 * only msgElement
 */
let chatMsgList = new Array();

const chatMsgConfig = {
    "chatOtherMsgBgColor" : "red",
    "chatMyMsgBgColor" : "lightblue",
    "chatMsgWidth" : "50",
    "chatMsgHeight" : "20"
}


/**
 * follow functions are called by ajax method
 * 
 * ajax method will takes specified room's chatting messsage and call follow functions
 */


/**
 * using paragraph element, create msg that is used in msg-container
 * @param {Message} msg 
 */
function createMsgElement(message)
{
    let msgEle = document.createElement("p");
    msgEle.innerHTML = message.msg;
    msgEle.style.width = chatMsgConfig.chatMsgWidth + "px";
    msgEle.style.height = chatMsgConfig.chatMsgHeight + "px";
    msgEle.style.backgroundColor = message.isMine() === true ? chatMsgConfig.chatMyMsgBgColor
                                                        : chatMsgConfig.chatOtherMsgBgColor;

    return msgEle;
}

/**
 * 메세지를 채팅 박스에 삽입
 */
function insertMsgElement(messageEle)
{
    let msgContainer = document.getElementsByClassName("msg-container")[0];
    msgContainer.append(messageEle);
}