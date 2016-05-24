package co.com.telefonica.atiempo.utiles;

public class Mensaje
{
	private String txtMsg;
	private byte[] messageId;
	private byte[] correlationId;
	private String replyToQueueManagerName;
	private String replyToQueueName;
	
	public String getTxtMsg()
	{
		return txtMsg;
	}

	public byte[] getMessageId()
	{
		return messageId;
	}

	public String getReplyToQueueManagerName()
	{
		return replyToQueueManagerName;
	}

	public String getReplyToQueueName()
	{
		return replyToQueueName;
	}

	public void setTxtMsg(String string)
	{
		txtMsg= string;
	}

	public void setMessageId(byte[] bs)
	{
		messageId= bs;
	}

	public void setReplyToQueueManagerName(String string)
	{
		replyToQueueManagerName= string;
	}

	public void setReplyToQueueName(String string)
	{
		replyToQueueName= string;
	}

	public void setCorrelationId(byte[] bs)
	{
		this.correlationId=bs;
	}


	public byte[] getCorrelationId()
	{
		return correlationId;
	}

}
