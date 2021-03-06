package com.sissi.pipeline.in.message;

import com.sissi.context.JIDContext;
import com.sissi.persistent.PersistentElementBox;
import com.sissi.pipeline.in.ProxyProcessor;
import com.sissi.protocol.Protocol;

/**
 * @author kim 2014年3月3日
 */
public class MessagePersistentProcessor extends ProxyProcessor {

	private final PersistentElementBox persistentElementBox;

	private final boolean log;

	public MessagePersistentProcessor(boolean log, PersistentElementBox persistentElementBox) {
		super();
		this.log = log;
		this.persistentElementBox = persistentElementBox;
	}

	@Override
	public boolean input(JIDContext context, Protocol protocol) {
		if (this.log) {
			this.persistentElementBox.push(protocol.parent().setFrom(context.jid()));
		}
		return true;
	}
}
