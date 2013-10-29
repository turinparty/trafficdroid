package it.localhost.trafficdroid.fragment;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.adapter.HeterogeneousArrayAdapter;
import it.localhost.trafficdroid.adapter.item.AbstractItem;
import it.localhost.trafficdroid.adapter.item.BadNewsDialogItem;
import it.localhost.trafficdroid.adapter.item.BannerDialogItem;
import it.localhost.trafficdroid.common.Utility;
import it.localhost.trafficdroid.dto.BadNewsDTO;
import it.localhost.trafficdroid.dto.StreetDTO;

import java.util.ArrayList;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.widget.ListView;

public class BadnewsDialogFragment extends DialogFragment {
	private static final String STREET = "STREET";

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Builder builder = new Builder(getActivity());
		StreetDTO street = (StreetDTO) getArguments().getSerializable(STREET);
		builder.setTitle(street.getName());
		ListView listview = (ListView) ListView.inflate(getActivity(), R.layout.dialog_badnews, null);
		ArrayList<AbstractItem> items = new ArrayList<AbstractItem>();
		if (!Utility.isAdFree(getActivity()))
			items.add(new BannerDialogItem(this, R.layout.iab_mrect));
		for (BadNewsDTO badNews : street.getBadNews())
			items.add(new BadNewsDialogItem(this, badNews));
		listview.setAdapter(new HeterogeneousArrayAdapter(getActivity(), items));
		builder.setView(listview);
		return builder.create();
	}

	public void show(FragmentManager fragmentManager, StreetDTO street) {
		Bundle arguments = new Bundle(2);
		arguments.putSerializable(STREET, street);
		setArguments(arguments);
		show(fragmentManager, BadnewsDialogFragment.class.getSimpleName());
	}
}