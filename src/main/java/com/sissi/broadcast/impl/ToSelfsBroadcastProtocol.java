package com.sissi.broadcast.impl;

import com.sissi.broadcast.BroadcastProtocol;
import com.sissi.context.JID;
import com.sissi.protocol.Protocol;

/**
 * @author kim 2014年1月15日
 */
public class ToSelfsBroadcastProtocol extends ToAnyBroadcastProtocol implements BroadcastProtocol {

	@Override
	public ToSelfsBroadcastProtocol broadcast(JID jid, Protocol protocol) {
		super.getAddressing().find(jid).write(protocol);
		return this;
	}
}