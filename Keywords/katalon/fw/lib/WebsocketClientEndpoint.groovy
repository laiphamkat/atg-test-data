package katalon.fw.lib

import java.nio.ByteBuffer
import java.time.Duration
import java.util.function.Function

import javax.websocket.ClientEndpoint
import javax.websocket.CloseReason
import javax.websocket.ContainerProvider
import javax.websocket.OnClose
import javax.websocket.OnMessage
import javax.websocket.OnOpen
import javax.websocket.Session
import javax.websocket.WebSocketContainer

import org.apache.commons.lang3.StringUtils
import org.openqa.selenium.support.ui.FluentWait
import org.openqa.selenium.support.ui.Wait

@ClientEndpoint
public class WebsocketClientEndpoint {

	Session userSession = null
	private MessageHandler messageHandler
	private String wsMessage = ''

	public WebsocketClientEndpoint(URI endpointURI) {
		try {
			WebSocketContainer container = ContainerProvider.getWebSocketContainer();
			container.connectToServer(this, endpointURI);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Callback hook for Connection open events.
	 *
	 * @param userSession the userSession which is opened.
	 */
	@OnOpen
	public void onOpen(Session userSession) {
		this.userSession = userSession
	}

	/**
	 * Callback hook for Connection close events.
	 *
	 * @param userSession the userSession which is getting closed.
	 * @param reason the reason for connection close
	 */
	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		this.userSession = null;
	}

	/**
	 * Callback hook for Message Events. This method will be invoked when a client send a message.
	 *
	 * @param message The text message
	 */
	@OnMessage
	public void onMessage(String message) {
		wsMessage += message + ','
	}

	@OnMessage
	public void onMessage(ByteBuffer bytes) {}

	/**
	 * register message handler
	 *
	 * @param msgHandler
	 */
	public void addMessageHandler(MessageHandler msgHandler) {
		this.messageHandler = msgHandler
	}

	/**
	 * Send a message.
	 *
	 * @param message
	 */
	public void sendMessage(String message) {
		this.userSession.getAsyncRemote().sendText(message)
	}

	public String getWSMessage () {
		return StringUtils.chop(this.wsMessage)
	}

	public boolean isClosed() {
		return this.userSession == null
	}

	public void waitUntilClosed(long timeOut, long every) {
		Thread.sleep(1000)

		Wait wait = new FluentWait('')
				.withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(every))

		wait.until(new Function<String, Boolean>(){
					public Boolean apply(String str) {
						return isClosed()
					}
				});
	}

	/**
	 * Message handler.
	 * 
	 */
	public static interface MessageHandler {
		public void handleMessage(String message)
	}
}