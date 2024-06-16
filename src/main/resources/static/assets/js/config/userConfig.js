/**
 * user config
 * 
 */

const userConfig = {
    "otherBgColor" : "lightblue",
    "myBgColor" : "lightsalmon",
    "userHeight" : "33",
    "userLineHeight" : "33",
    "userClassName" : "user",
    "userBorderRadius" : "10"
}


/**
 * follow functions are called by ajax method
 * 
 * ajax method will takes specified room's user and call follow functions
 */


/**
 * using paragraph element, create user that is used in user-container
 * @param {user} msg 
 */
function createUserElement(userObj)
{
    let user = document.createElement("p");
    user.innerHTML = userObj.id;
    user.className = userConfig.userClassName;
    user.style.height = userConfig.userHeight + "px"
    user.style.lineHeight = userConfig.userLineHeight + "px"
    user.style.borderRadius = userConfig.userBorderRadius + "px"

    if(userObj.isMe)
    {
        user.style.backgroundColor = userConfig.myBgColor
    }else
    {
        user.style.backgroundColor = userConfig.otherBgColor
    }

    return user;
}

/**
 * 메세지를 채팅 박스에 삽입
 */
function insertUserElement(userEle)
{
    let userContainer = document.getElementsByClassName("user-container")[0];
    userContainer.append(userEle);
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