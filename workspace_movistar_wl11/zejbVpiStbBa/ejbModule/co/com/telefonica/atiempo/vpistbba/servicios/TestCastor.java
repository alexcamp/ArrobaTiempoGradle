/*
 * Created on Apr 24, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.vpistbba.servicios;

import co.com.telefonica.atiempo.interfaces.atiempo.TR001S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.XMLUtilities;

/**
 * @author 805538
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TestCastor {
	public static void main(String[] args) throws ATiempoAppEx {
		String m1 = "<?xml version=\"1.0\"?><atiempo:tr-001-s xmlns:atiempo=\"http://atiempo.telefonica.com.co\"" +
				" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" " +
				"xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
				"xmlns:xml=\"http://www.w3.org/XML/1998/namespace\"> " +
				"<head><id>1841711</id></head><body><request-number>1841711</request-number><emitting-user>FA51119         </emitting-user><emitting-date>2008-02-11T09:03:12</emitting-date><observations>                                                                                                    </observations><client-data><client-document-type>CC </client-document-type><client-document-number>8688792             </client-document-number><client-document-verification>  </client-document-verification><client-name>KUZMAR GARZON YAMAL                                             </client-name><client-lastname>-                                       </client-lastname><client-lastname2>                                        </client-lastname2><client-segment-code>1</client-segment-code><client-subsegment-code>3</client-subsegment-code><account-segment-code>7</account-segment-code><account-subsegment-code>22</account-subsegment-code><client-type>F</client-type></client-data><contact-data><submitter-name>                                                                </submitter-name><submitter-last-name>                                        </submitter-last-name><submitter-second-last-name>                                        </submitter-second-last-name><contact-phone>53407080            </contact-phone></contact-data><sale-channel>57</sale-channel><groupings><group><commitment-date>2008-02-12T09:03:12</commitment-date><observations>                                                                                                    </observations><code>1</code><father-group-code>0</father-group-code><children-group-count>0</children-group-count><comercial-product-identification-number>53575662                                </comercial-product-identification-number><comercial-product-identification>L </comercial-product-identification><comercial-operation-type>25</comercial-operation-type><comercial-product-code>863206600</comercial-product-code><father-comercial-product-code>0</father-comercial-product-code><comercial-product-type>1</comercial-product-type><comercial-product-subtype>1</comercial-product-subtype><use-type-code>6</use-type-code><use-type-name>R ESTRATO 6 ALTO    </use-type-name><father-identification-line>                                        </father-identification-line><address><street-number>-     </street-number><department-code>08</department-code><city-code>08001000 </city-code><street-name>KRA 57    CLL 85    NO 57               </street-name><path-type></path-type><path-number>0</path-number><first-path-characters></first-path-characters><second-path-characters></second-path-characters><path-zone></path-zone><path-type-2></path-type-2><path-number-2>0</path-number-2><first-path-characters-2></first-path-characters-2><second-path-characters-2></second-path-characters-2><path-zone-2></path-zone-2><street-type>NOE</street-type><place-type-1></place-type-1><place-number-1></place-number-1><place-type-2></place-type-2><place-number-2></place-number-2><place-type-3></place-type-3><place-number-3></place-number-3><complements-description-1>                                        </complements-description-1><complements-description-2>                                        </complements-description-2><sub-city-name>SIN DEFINIR         </sub-city-name><external-city-code>08001000 </external-city-code><territory>    </territory></address><subRequests><subRequest><product-service-code>80</product-service-code><product-service-name>LLAMADA EN ESPERA   </product-service-name><comercial-operation>6</comercial-operation><product-service-type>2</product-service-type><quantity-of-product-services>1</quantity-of-product-services><service-initial-date>2008-02-12T09:03:12</service-initial-date><service-end-date>9999-12-31T00:00:00</service-end-date><observations>                                                                                                    </observations><code>1</code><contract-number>0</contract-number><characteristic></characteristic></subRequest></subRequests></group></groupings></body></atiempo:tr-001-s>";
		//Esto para hacer el cache
//		long start = System.currentTimeMillis();
//		TR001S tr001s = (TR001S) XMLUtilities.unmarshall(m1);
//		long interval = System.currentTimeMillis() - start;
	

//						   "ClientDocumentNumber: " + tr001s.getClientDocumentNumber() + " " +
//						   "RequestNumber: " + tr001s.getRequestNumber() + " time: " + interval + " ms.");
		int firstRange = 5;
		for (int i = 1; i <= firstRange; i++) {
			System.out.println("Starting first range of threads - thread number: " + i);
			Thread t1 = new Thread(new TestRun(m1, i));
			t1.start();
		}
		System.out.println("Sleeping first 10 seconds");		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = firstRange + 1; i <= firstRange + 25; i++) {
			
			Thread t1 = new Thread(new TestRun(m1, i));
			t1.start();
		}
		System.out.println("Sleeping last 10 seconds");		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("END Test");
	}
	
	private static class TestRun implements Runnable {
		private String msg;
		private int id;
		
		public TestRun(String m1, int i) {
			this.msg = m1;
			this.id = i;
		}
		public void run() {
			try {
				convertirMensaje(msg);		
			} catch (Throwable e) {
				System.err.println("Id Thread: " + id + " " + e);
				e.printStackTrace();
			}
		}
		
		public void convertirMensaje(String mensaje) throws ATiempoAppEx {
			long start = System.currentTimeMillis();
			TR001S tr001s = (TR001S) XMLUtilities.unmarshall(mensaje);
			long interval = System.currentTimeMillis() - start;
			//System.out.println("Thread_ "+id+" Unmarshal result: " + tr001s);	
			System.out.println("Thread_"+id+" Unmarshal result -- ClientType: " + tr001s.getClientType() + " " +
							   "ClientDocumentNumber: " + tr001s.getClientDocumentNumber() + " " +
							   "RequestNumber: " + tr001s.getRequestNumber() + " time: " + interval + " ms.");
		}	
		
	}
}
