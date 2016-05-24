//- Copyright Notice
//-----------------------------------------------------------------------
// (C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
// Bogota, Colombia All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: TR006E.java,v 1.1 2011/03/30 18:23:29 lfmartinez Exp $
//-----------------------------------------------------------------------
package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author ylapchik
 * @version $Revision: 1.1 $
 */
public class TR006E extends ResponseHeader {
	private String codAveCd;
	private String codCieAveCd;
	private long codAreGesCd;
	private char codEstAvecd;
	private char indCobVstIn;
	private char indDftEmoIn;
	private char ajuFltSerIn;
	private char indImpMltIn;
	private char indSerEfeIn;
	private char indEquAtcIn;
	private char fecCieAveTs;
	private char idRequestAtis;

	public char getAjuFltSerIn() {
		return ajuFltSerIn;
	}

	public long getCodAreGesCd() {
		return codAreGesCd;
	}

	public String getCodAveCd() {
		return codAveCd;
	}

	public String getCodCieAveCd() {
		return codCieAveCd;
	}

	public char getCodEstAvecd() {
		return codEstAvecd;
	}

	public char getFecCieAveTs() {
		return fecCieAveTs;
	}

	public char getIdRequestAtis() {
		return idRequestAtis;
	}

	public char getIndCobVstIn() {
		return indCobVstIn;
	}

	public char getIndDftEmoIn() {
		return indDftEmoIn;
	}

	public char getIndEquAtcIn() {
		return indEquAtcIn;
	}

	public char getIndImpMltIn() {
		return indImpMltIn;
	}

	public char getIndSerEfeIn() {
		return indSerEfeIn;
	}

	public void setAjuFltSerIn(char c) {
		ajuFltSerIn = c;
	}

	public void setCodAreGesCd(long l) {
		codAreGesCd = l;
	}

	public void setCodAveCd(String l) {
		codAveCd = l;
	}

	public void setCodCieAveCd(String string) {
		codCieAveCd = string;
	}

	public void setCodEstAvecd(char c) {
		codEstAvecd = c;
	}

	public void setFecCieAveTs(char c) {
		fecCieAveTs = c;
	}

	public void setIdRequestAtis(char c) {
		idRequestAtis = c;
	}

	public void setIndCobVstIn(char c) {
		indCobVstIn = c;
	}

	public void setIndDftEmoIn(char c) {
		indDftEmoIn = c;
	}

	public void setIndEquAtcIn(char c) {
		indEquAtcIn = c;
	}

	public void setIndImpMltIn(char c) {
		indImpMltIn = c;
	}

	public void setIndSerEfeIn(char c) {
		indSerEfeIn = c;
	}
	public int hashCode() {
		return codCieAveCd.hashCode();
	}
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR006E) {
			TR006E other = (TR006E) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(codCieAveCd, other.codCieAveCd)
				&& EqualityUtilities.equals(codAveCd,other.codAveCd)
				&& codAreGesCd == other.codAreGesCd
				&& codEstAvecd == other.codEstAvecd
				&& indCobVstIn == other.indCobVstIn
				&& indDftEmoIn == other.indDftEmoIn
				&& ajuFltSerIn == other.ajuFltSerIn
				&& indImpMltIn == other.indImpMltIn
				&& indSerEfeIn == other.indSerEfeIn
				&& indEquAtcIn == other.indEquAtcIn
				&& fecCieAveTs == other.fecCieAveTs
				&& idRequestAtis == other.idRequestAtis;
		}
		return false;
	}
}
