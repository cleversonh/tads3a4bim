package br.univel.rest;

import javax.ws.rs.core.Application;
import javax.ws.rs.ApplicationPath;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;


 

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/ExpiryQueue") }, mappedName = "java:/jms/queue/ExpiryQueue")
@ApplicationPath("/rest")
public class RestApplication extends Application
{
	
	public AlunoQueueMDB() {
		 
	}
 
	public void onMessage(Message message) {
		System.out.println(getClass() + " Inicio");
		try {
 
			if (message instanceof TextMessage) {
				TextMessage mensagem = (TextMessage) message;
				System.out.println("Mensagem recebida: " + mensagem.getText());
			} else if (message instanceof ObjectMessage) {
				try {
					ObjectMessage obj = (ObjectMessage) message;
					Aluno aluno = (Aluno) obj.getObject();
					System.out.println("Objeto Recebido: " + aluno);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(getClass() + " Fim");
	}
	
}