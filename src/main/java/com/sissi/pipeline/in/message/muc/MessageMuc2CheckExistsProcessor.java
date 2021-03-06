package com.sissi.pipeline.in.message.muc;

import com.sissi.context.JIDContext;
import com.sissi.pipeline.in.ProxyProcessor;
import com.sissi.protocol.Error;
import com.sissi.protocol.Protocol;
import com.sissi.protocol.ProtocolType;
import com.sissi.protocol.error.ServerError;
import com.sissi.protocol.error.detail.ItemNotFound;
import com.sissi.ucenter.muc.MucFinder;

/**
 * @author kim 2014年3月10日
 */
public class MessageMuc2CheckExistsProcessor extends ProxyProcessor {

	private final Error error = new ServerError().setType(ProtocolType.CANCEL).add(ItemNotFound.DETAIL);

	private final MucFinder mucFinder;

	public MessageMuc2CheckExistsProcessor(MucFinder mucFinder) {
		super();
		this.mucFinder = mucFinder;
	}

	@Override
	public boolean input(JIDContext context, Protocol protocol) {
		return this.mucFinder.exists(super.build(protocol.getTo())) ? true : this.writeAndReturn(context, protocol);
	}

	private boolean writeAndReturn(JIDContext context, Protocol protocol) {
		context.write(protocol.parent().reply().setError(this.error));
		return false;
	}
}
