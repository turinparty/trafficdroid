package it.localhost.trafficdroid.adapter.item;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.dto.StreetDTO;
import android.content.Context;
import android.view.View;

public class GraphItem extends AbstractItem {
	private StreetDTO streetDTO;

	public GraphItem(Context context, StreetDTO streetDTO) {
		super(context);
		this.streetDTO = streetDTO;
	}

	@Override
	public int getType() {
		return itemTypes[5];
	}

	@Override
	public View inflateView() {
		View view = inflater.inflate(R.layout.main_item_graph, null, false);
		return view;
	}

	@Override
	public void fillView(View view) {
	}

	@Override
	public void onClick() {
		((OnGraphItemClickListener) context).onGraphItemClick(streetDTO.getGraph());
	}

	public interface OnGraphItemClickListener {
		public void onGraphItemClick(String graph);
	}
}
