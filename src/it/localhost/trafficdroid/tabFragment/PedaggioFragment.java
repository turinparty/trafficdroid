package it.localhost.trafficdroid.tabFragment;

import com.google.ads.AdRequest;
import com.google.ads.InterstitialAd;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.activity.AbstractActivity;
import it.localhost.trafficdroid.common.ListExit;
import it.localhost.trafficdroid.common.TdAdListener;
import it.localhost.trafficdroid.dao.PedaggioService;
import it.localhost.trafficdroid.dto.BaseDTO;
import it.localhost.trafficdroid.dto.PedaggioDTO;
import it.localhost.trafficdroid.fragment.MessageDialogFragment;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

public class PedaggioFragment extends Fragment implements TabListener {
	private TextView result;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.pedaggio, null);
		result = (TextView) v.findViewById(R.id.result);
		getActivity().setProgressBarIndeterminateVisibility(false);
		final AutoCompleteTextView moneyFrom = (AutoCompleteTextView) v.findViewById(R.id.moneyFrom);
		final AutoCompleteTextView moneyTo = (AutoCompleteTextView) v.findViewById(R.id.moneyTo);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, ListExit.getInstance().getKeys());
		moneyFrom.setAdapter(adapter);
		moneyTo.setAdapter(adapter);
		v.findViewById(R.id.ok).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String from = ListExit.getInstance().get(moneyFrom.getText().toString()).toString();
				String to = ListExit.getInstance().get(moneyTo.getText().toString()).toString();
				if (from != null && to != null)
					new PedaggioAsyncTask().execute(from, to);
				else
					new MessageDialogFragment().show(getFragmentManager(), getString(R.string.error), getString(R.string.wrongData), false);
			}
		});
		if (!((AbstractActivity) getActivity()).isInterstitialFree()) {
			InterstitialAd interstitial = new InterstitialAd(getActivity(), getString(R.string.adUnitId));
			interstitial.setAdListener(new TdAdListener());
			AdRequest adRequest = new AdRequest();
			adRequest.addTestDevice(getString(R.string.testDevices));
			interstitial.loadAd(adRequest);
		}
		return v;
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		ft.replace(android.R.id.content, this);
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}

	private class PedaggioAsyncTask extends PedaggioService {
		@Override
		protected void onPreExecute() {
			getActivity().setProgressBarIndeterminateVisibility(true);
			((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}

		@Override
		protected void onPostExecute(BaseDTO dto) {
			getActivity().setProgressBarIndeterminateVisibility(false);
			if (dto.isSuccess())
				result.setText("€ " + ((PedaggioDTO) dto).getPedaggio());
			else
				new MessageDialogFragment().show(getFragmentManager(), getString(R.string.error), dto.getMessage(), false);
		}
	}
}
