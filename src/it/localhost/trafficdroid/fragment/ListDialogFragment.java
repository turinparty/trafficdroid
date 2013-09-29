package it.localhost.trafficdroid.fragment;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;

public class ListDialogFragment extends DialogFragment {
	private static final String TITLE = "title";
	private static final String LIST = "list";
	private OnClickListener onClickListener;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Builder builder = new Builder(getActivity());
		builder.setTitle(getArguments().getInt(TITLE));
		builder.setItems(getArguments().getStringArray(LIST), onClickListener);
		return builder.create();
	}

	public void show(FragmentManager fragmentManager, int title, String[] list, OnClickListener onClickListener) {
		this.onClickListener = onClickListener;
		Bundle bundle = new Bundle(2);
		bundle.putStringArray(LIST, list);
		bundle.putInt(TITLE, title);
		setArguments(bundle);
		show(fragmentManager, ListDialogFragment.class.getSimpleName());
	}
}
