package com.cictec.middleware.minieye.tcp.code;

import com.cictec.middleware.minieye.tcp.annotation.TerminalAdditionalMessageDecoder;
import com.cictec.middleware.minieye.tcp.annotation.TerminalAdditionalMessageEncoder;
import com.cictec.middleware.minieye.tcp.annotation.TerminalMessageDecoder;
import com.cictec.middleware.minieye.tcp.annotation.TerminalMessageEncoder;
import com.impetus.annovention.ClasspathDiscoverer;
import com.impetus.annovention.listener.ClassAnnotationDiscoveryListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("codecFounder")
public class AnnotationCodecFounder implements CodecFounder {

	private static Logger logger = LoggerFactory.getLogger(AnnotationCodecFounder.class);

	private Map<Integer, MessageDecoder> decoders = new HashMap<Integer, MessageDecoder>();
	private Map<Integer, Map<Integer, MessageAdditionalDecoder>> addtionalDecoders = new HashMap<Integer, Map<Integer, MessageAdditionalDecoder>>();
	private Map<Integer, MessageEncoder> encoders = new HashMap<Integer, MessageEncoder>();
	private Map<Integer, Map<Integer, MessageAdditionalEncoder>> addtionalEncoders = new HashMap<Integer, Map<Integer, MessageAdditionalEncoder>>();

	public AnnotationCodecFounder() {
		loadDecoders();
	}

	@Override
	public MessageDecoder getDecoder(int messageId) {
		return decoders.get(messageId);
	}

	@Override
	public MessageAdditionalDecoder getDecoder(int messageId, int addtionalId) {
		Map<Integer, MessageAdditionalDecoder> ads = addtionalDecoders.get(messageId);
		if (ads == null) {
			return null;
		}
		return ads.get(addtionalId);
	}

	@Override
	public MessageEncoder getEecoder(int messageId) {
		return encoders.get(messageId);
	}

	@Override
	public MessageAdditionalEncoder getEncoder(int messageId, int addtionalId) {
		Map<Integer, MessageAdditionalEncoder> aes = addtionalEncoders.get(messageId);
		if (aes == null) {
			return null;
		}
		return aes.get(addtionalId);
	}

	public void showAllDecoders() {
		for (Integer key : decoders.keySet()) {
			System.out.println(key + "   " + decoders.get(key).getClass().getName());
		}
	}

	static public void main(String[] args) {
		AnnotationCodecFounder df = new AnnotationCodecFounder();
		df.showAllDecoders();
	}

	/**
	 * 注册加载：加载通信协议编解码 处理类
	 * ，加载注解为【@TerminalMessageDecoder、@TerminalMessageEncoder】的编解码器
	 */
	private void loadDecoders() {

		ClasspathDiscoverer discoverer = new ClasspathDiscoverer();

		discoverer.addAnnotationListener(new DecoderAnnotationDiscoveryListener());
		discoverer.addAnnotationListener(new AdditionalDecoderAnnotationDiscoveryListener());

		discoverer.addAnnotationListener(new EncoderAnnotationDiscoveryListener());
		discoverer.addAnnotationListener(new AdditionalEncoderAnnotationDiscoveryListener());

		discoverer.discover(true, false, false, true, true);
	}

	class DecoderAnnotationDiscoveryListener implements ClassAnnotationDiscoveryListener {

		@Override
		public void discovered(String clazz, String annotation) {

			try {
				Class<?> clz = Class.forName(clazz);
				int messageId = clz.getAnnotation(TerminalMessageDecoder.class).value();
				AbstractMessageDecoder md = (AbstractMessageDecoder) clz.newInstance();
				md.setCodecFounder(AnnotationCodecFounder.this);
				decoders.put(messageId, md);
			} catch (Exception e) {
				e.printStackTrace();
			}

			logger.debug("Discovered Class(" + clazz + ") with Annotation(" + annotation + ")");
		}

		@Override
		public String[] supportedAnnotations() {
			return new String[] { TerminalMessageDecoder.class.getName() };
		}
	}

	class AdditionalDecoderAnnotationDiscoveryListener implements ClassAnnotationDiscoveryListener {

		@Override
		public void discovered(String clazz, String annotation) {

			try {
				Class<?> clz = Class.forName(clazz);
				TerminalAdditionalMessageDecoder tama = clz.getAnnotation(TerminalAdditionalMessageDecoder.class);
				int messageId = tama.messageId();
				int addtionalId = tama.addtionalId();

				MessageAdditionalDecoder md = (MessageAdditionalDecoder) clz.newInstance();

				Map<Integer, MessageAdditionalDecoder> ads = addtionalDecoders.get(messageId);
				if (ads == null) {
					ads = new HashMap<Integer, MessageAdditionalDecoder>();
					addtionalDecoders.put(messageId, ads);
				}
				ads.put(addtionalId, md);

			} catch (Exception e) {
				e.printStackTrace();
			}

			logger.info("Discovered Class(" + clazz + ") with Annotation(" + annotation + ")");
		}

		@Override
		public String[] supportedAnnotations() {
			return new String[] { TerminalAdditionalMessageDecoder.class.getName() };
		}
	}

	class EncoderAnnotationDiscoveryListener implements ClassAnnotationDiscoveryListener {

		@Override
		public void discovered(String clazz, String annotation) {

			try {
				Class<?> clz = Class.forName(clazz);
				int messageId = clz.getAnnotation(TerminalMessageEncoder.class).value();
				AbstractMessageEncoder md = (AbstractMessageEncoder) clz.newInstance();
				md.setCodecFounder(AnnotationCodecFounder.this);
				encoders.put(messageId, md);
			} catch (Exception e) {
				e.printStackTrace();
			}

			logger.debug("Discovered Class(" + clazz + ") with Annotation(" + annotation + ")");
		}

		@Override
		public String[] supportedAnnotations() {
			return new String[] { TerminalMessageEncoder.class.getName() };
		}
	}

	class AdditionalEncoderAnnotationDiscoveryListener implements ClassAnnotationDiscoveryListener {

		@Override
		public void discovered(String clazz, String annotation) {

			try {
				Class<?> clz = Class.forName(clazz);
				TerminalAdditionalMessageEncoder tama = clz.getAnnotation(TerminalAdditionalMessageEncoder.class);
				int messageId = tama.messageId();
				int addtionalId = tama.addtionalId();

				MessageAdditionalEncoder md = (MessageAdditionalEncoder) clz.newInstance();

				Map<Integer, MessageAdditionalEncoder> ads = addtionalEncoders.get(messageId);
				if (ads == null) {
					ads = new HashMap<Integer, MessageAdditionalEncoder>();
					addtionalEncoders.put(messageId, ads);
				}
				ads.put(addtionalId, md);

			} catch (Exception e) {
				e.printStackTrace();
			}

			logger.debug("Discovered Class(" + clazz + ") with Annotation(" + annotation + ")");
		}

		@Override
		public String[] supportedAnnotations() {
			return new String[] { TerminalAdditionalMessageEncoder.class.getName() };
		}
	}
}
