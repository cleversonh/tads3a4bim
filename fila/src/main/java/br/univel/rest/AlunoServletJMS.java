package br.univel.rest;
 
import java.io.IOException;
import java.io.PrintWriter;
 
import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
 
@WebServlet("/AlunoServletJMS")
public class AlunoServletJMS extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	@Resource(mappedName = "java:/queue/alunoQueue")
	private Queue fila;
 
	@Resource(mappedName = "java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;
 
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
 
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println(getClass() + "Inicio........");
		Aluno aluno = new Aluno();
		aluno.setMatricula(2221L);
		aluno.setNome("Maria da Silva");
		enviarObjetoForma1(aluno);
		System.out.println(getClass() + "Fim........");
 
		PrintWriter out = response.getWriter();		
		out.print("<H1>Objeto enviado com sucesso! JMS 1.0</H1>");	
	}
 
	/**
	 *  Envia um objeto complexo para a fila JMS
	 *  
	 * @param aluno Classe de entidade
	 * Esta forma de envio é a mais conhecida e utilizada atualmente
	 */
	public void enviarObjetoForma1(Aluno aluno) {
		try {
			Connection connection = connectionFactory.createConnection();
			try {
				Session session = connection.createSession(false,
						Session.AUTO_ACKNOWLEDGE);
				ObjectMessage objMessage = session.createObjectMessage();
		        objMessage.setObject(aluno);
 
				MessageProducer messageProducer = session.createProducer(fila);
				messageProducer.send(objMessage);
				messageProducer.close();
			} finally {
				connection.close();
			}
		} catch (JMSException ex) {
			ex.printStackTrace();
		}
	}	
}