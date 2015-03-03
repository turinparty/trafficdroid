package it.localhost.trafficdroid.fragment.dialog;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;


public class MessageDialogFragment extends DialogFragment {
	private static final String KEY_TITLE = "KEY_TITLE";
	private static final String KEY_MSG = "KEY_MSG";
	private static final String KEY_EXIT = "KEY_EXIT";

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Builder builder = new Builder(getActivity());
		builder.setTitle(getArguments().getString(KEY_TITLE));
		builder.setMessage(getArguments().getString(KEY_MSG));
		builder.setPositiveButton(android.R.string.ok, getArguments().getBoolean(KEY_EXIT) ? new OnClickExitListener() : null);
		setCancelable(false);
		builder.setCancelable(false);
		return builder.create();
	}

	public void show(FragmentManager fragmentManager, String title, String msg, boolean exit) {
		Bundle arguments = new Bundle(2);
		arguments.putString(KEY_TITLE, title);
		arguments.putString(KEY_MSG, msg);
		arguments.putBoolean(KEY_EXIT, exit);
		setArguments(arguments);
		show(fragmentManager, MessageDialogFragment.class.getSimpleName());
	}

	private final class OnClickExitListener implements OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			getActivity().finish();
		}
	}
}