package com.telefonica_chile.bandeja.web.utiles.msgbroker;

public class MBOutput {
	private MBCampo[] header;	// formato y valor de los campos
	private MBCampo[] tplBody;	// solo los formatos de los campos
	private String[][] body;	// tiene los valores
	

	public MBOutput(MBCampo[] header, MBCampo[] tplBody) {
		this.header = header;
		this.tplBody = tplBody;
	}

	public MBOutput(MBCampo[] header, MBCampo[] tplBody, String[][] data) {
		this(header, tplBody);
		this.body = data;
	}
	
	
	public String[][] getOutput() {
		return body;
	}

	
	public MBCampo[] getTplBody() {
		return tplBody;
	}
	
	public void setTplBody(MBCampo[] tplBody) {
		this.tplBody = tplBody;
	}
	
	public MBCampo getTplCampoByName(String name) {
		if (tplBody == null)
			return null;
			
		for (int i = 0; i < tplBody.length; i++)
			if (tplBody[i].getNombre().equals(name))
				return tplBody[i];

		return null;
	}
}

