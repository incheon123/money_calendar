/**
 * 완성된 채팅 메세지만 넣으십쇼
 * only msgElement
 */
let chatMsgList = new Array();



export class Message
{
    #msg_id;
    #sender; //User object...
    #receiver;
    #msg;
    #type;
    #isMine;
    #create_date;

    constructor(msg_id, sender, receiver, msg, type, isMine, create_date)
    {
        this.#msg_id      = msg_id;
        this.#sender      = sender;
        this.#receiver    = receiver;
        this.#msg         = msg;
        this.#type        = type;
        this.#isMine      = isMine;
        this.#create_date = create_date;
    }

    get isMine()
    {
        return this.#isMine;
    }
    get msg_id()
    {
        return this.#msg_id;
    }
    get sender()
    {
        return this.#sender;
    }
    get receiver()
    {
        return this.#receiver;
    }
    get msg()
    {
        return this.#msg;
    }
    get type()
    {
        return this.#type;
    }
    get createDate()
    {
        return this.#create_date;
    }
    
}
