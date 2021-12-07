package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.FlagTerm;

public class GmailInbox {

	public static void main(String[] args) {
		gmailPop3("testautomation1298@gmail.com","Km@noher1298");

	}
	
	public static void gmailPop3(String user, String pwd) {
		try {
			// create properties
			Properties properties = new Properties();

			properties.put("mail.imap.host", "imap.gmail.com");
			properties.put("mail.imap.port", "993");
			properties.put("mail.imap.starttls.enable", "true");
			properties.put("mail.imap.ssl.trust", "imap.gmail.com");

			Session emailSession = Session.getDefaultInstance(properties);

			// create the imap store object and connect to the imap server
			Store store = emailSession.getStore("imaps");

			store.connect("imap.gmail.com", user, pwd);

			// create the inbox object and open it
			Folder inbox = store.getFolder("Inbox");
			inbox.open(Folder.READ_WRITE);

			// retrieve the messages from the folder in an array and print it
			Message[] messages = inbox.search(new FlagTerm(new Flags(Flag.SEEN), false));
			System.out.println("messages.length---" + messages.length);

			for (int i = 0, n = messages.length; i < n; i++) {
				Message message = messages[i];
				message.setFlag(Flag.SEEN, true);
				System.out.println("---------------------------------");
				System.out.println("Email Number " + (i + 1));
				System.out.println("Subject: " + message.getSubject());
				System.out.println("From: " + message.getFrom()[0]);
				System.out.println("Text: " + message.getContent().toString());

			}

			inbox.close(false);
			store.close();

		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void read() {

		Properties props = new Properties();

		try {
			props.load(new FileInputStream(new File(DriverUtility.baseDir + "\\src\\main\\resources\\TestData\\smtp.properties")));
			
			Session session = Session.getDefaultInstance(props, null);

			Store store = session.getStore("imaps");
			store.connect("smtp.gmail.com", "testautomation1298@gmail.com", "Km@noher1298");

			Folder inbox = store.getFolder("inbox");
			inbox.open(Folder.READ_ONLY);
			int messageCount = inbox.getMessageCount();

			System.out.println("Total Messages:- " + messageCount);

			Message[] messages = inbox.getMessages();
			System.out.println("------------------------------");

			for (int i = 0; i < 10; i++) {
				System.out.println("Mail Subject:- " + messages[i].getSubject());
			}

			inbox.close(true);
			store.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}