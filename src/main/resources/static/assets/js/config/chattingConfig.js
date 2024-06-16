import {Message} from "../chat/chatMessage.js";

const chatMsgConfig = {
    "chatOtherMsgBgColor" : "red",
    "chatMyMsgBgColor" : "lightblue",
    "chatMyMsgPos" : "right",
    "chatOtherMsgPos" : "left",
    "chatMsgHeight" : "40",
    "chatMsgLineHeight" : "40",
    "chatMsgClassName" : "chatMsg",
    "chatMsgBorderRadius" : "10"
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
    msgEle.className = chatMsgConfig.chatMsgClassName;
    msgEle.style.height = chatMsgConfig.chatMsgHeight + "px"
    msgEle.style.lineHeight = chatMsgConfig.chatMsgLineHeight + "px"
    msgEle.style.borderRadius = chatMsgConfig.chatMsgBorderRadius + "px"

    if(message.isMine)
    {
        msgEle.style.backgroundColor = chatMsgConfig.chatMyMsgBgColor
        msgEle.style.textAlign = chatMsgConfig.chatMyMsgPos;
    }else
    {
        msgEle.style.backgroundColor = chatMsgConfig.chatOtherMsgBgColor
        msgEle.style.textAlign = chatMsgConfig.chatOtherMsgPos;
    }

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


// let msg1 = new Message(111, "a", "roomId#11111111", "테스트 1", "text", true, "06-14");
// let msg2 = new Message(112, "a", "roomId#11111111", "테스트 2", "text", false, "06-14");
// let msg3 = new Message(112, "a", "roomId#11111111", "테스트 2", "text", true, "06-14");
// let msgEle1 = createMsgElement(msg1);
// let msgEle2 = createMsgElement(msg2);
// let msgEle3 = createMsgElement(msg3);
// insertMsgElement(msgEle1)
// insertMsgElement(msgEle2)
// insertMsgElement(msgEle3)
// console.log(msgEle1)