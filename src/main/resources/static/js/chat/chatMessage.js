class Message
{
    #msg_id;
    #sender;
    #receiver;
    #msg;
    #type;
    #create_date;

    constructor(msg_id, sender, receiver, msg, type, create_date)
    {
        this.#msg_id = msg_id;
        this.#sender = sender;
        this.#receiver = receiver;
        this.#msg = msg;
        this.#type = type;
        this.#create_date = create_date;
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

/**
 * 메세지를 채팅 박스에 삽입
 */
function insertMsg()
{

}