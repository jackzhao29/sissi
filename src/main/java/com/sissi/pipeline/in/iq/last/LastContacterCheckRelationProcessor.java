package com.sissi.pipeline.in.iq.last;

import com.sissi.context.JIDContext;
import com.sissi.pipeline.in.ProxyProcessor;
import com.sissi.protocol.Error;
import com.sissi.protocol.Protocol;
import com.sissi.protocol.ProtocolType;
import com.sissi.protocol.error.ServerError;
import com.sissi.protocol.error.detail.Forbidden;
import com.sissi.protocol.iq.last.Last;
import com.sissi.protocol.iq.roster.RosterSubscription;
import com.sissi.ucenter.RelationRoster;

/**
 * @author kim 2014年1月26日
 */
public class LastContacterCheckRelationProcessor extends ProxyProcessor {

	private final Error error = new ServerError().setType(ProtocolType.CANCEL).add(Forbidden.DETAIL);

	private final String[] relations = new String[] { RosterSubscription.TO.toString(), RosterSubscription.BOTH.toString() };

	@Override
	public boolean input(JIDContext context, Protocol protocol) {
		return RelationRoster.class.cast(super.ourRelation(context.jid(), super.build(protocol.getParent().getTo()))).in(this.relations) ? true : this.writeAndReturn(context, Last.class.cast(protocol));
	}

	private boolean writeAndReturn(JIDContext context, Last last) {
		context.write(last.getSeconds().getParent().reply().setError(this.error));
		return false;
	}
}
