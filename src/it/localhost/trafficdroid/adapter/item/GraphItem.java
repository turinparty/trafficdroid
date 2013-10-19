package it.localhost.trafficdroid.adapter.item;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.activity.MainActivity;
import it.localhost.trafficdroid.dto.StreetDTO;
import it.localhost.trafficdroid.fragment.WebviewDialogFragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Fragment;
import android.view.View;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.MapBuilder;

public class GraphItem extends AbstractItem {
	private static final String firstUrl = "http://vai-cdn.stradeanas.it/Appscripts/sinotraffic.php?city=";
	private static final String secondUrl = "&ts=";
	private StreetDTO streetDTO;

	public GraphItem(Fragment fragment, StreetDTO streetDTO) {
		super(fragment);
		this.streetDTO = streetDTO;
	}

	@Override
	public int getType() {
		return itemTypes[5];
	}

	@Override
	public View inflateView() {
		return View.inflate(fragment.getActivity(), R.layout.main_item_graph, null);
	}

	@Override
	public void fillView(View view) {
	}

	@Override
	public void onClick() {
		EasyTracker.getInstance(fragment.getActivity()).send(MapBuilder.createEvent(MainActivity.EVENT_CAT_GRAPH, MainActivity.EVENT_ACTION_OPEN, streetDTO.getGraph(), (long) 0).build());
		new WebviewDialogFragment().show(fragment.getFragmentManager(), firstUrl + streetDTO.getGraph() + secondUrl + new SimpleDateFormat("yyyyMMddHHmm", Locale.getDefault()).format(new Date()), null);
	}
}
