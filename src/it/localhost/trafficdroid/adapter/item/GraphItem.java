package it.localhost.trafficdroid.adapter.item;

import it.localhost.trafficdroid.R;
import android.app.Fragment;
import android.view.View;

public class GraphItem extends AbstractItem {
	public GraphItem(Fragment fragment, String graph) {
		super(fragment, graph);
	}

	@Override
	public View inflateView() {
		return View.inflate(fragment.getActivity(), R.layout.main_item_graph, null);
	}

	@Override
	public void fillView(View view) {
	}
}
