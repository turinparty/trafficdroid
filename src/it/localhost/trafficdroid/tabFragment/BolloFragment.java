package it.localhost.trafficdroid.tabFragment;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.activity.MainActivity;
import it.localhost.trafficdroid.common.AdManager;
import it.localhost.trafficdroid.common.AutoFocusTextWatcher;
import it.localhost.trafficdroid.fragment.WebviewDialogFragment;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.ads.AdView;

public class BolloFragment extends Fragment implements TabListener {
	private static final String bolloUrl = "https://servizi.aci.it/Bollonet/calcolo.do?LinguaSelezionata=ita&CodiceServizio=2&TipoVeicolo=";
	private static final String param1 = "&RegioneResidenza=";
	private static final String param2 = "&Targa=";
	private EditText targaA, targaB, targaC;
	private Spinner tipoVeicolo, regioneResidenza;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.bollo, container, false);
		targaA = (EditText) v.findViewById(R.id.targaA);
		targaB = (EditText) v.findViewById(R.id.targaB);
		targaC = (EditText) v.findViewById(R.id.targaC);
		new AutoFocusTextWatcher(targaA, 2);
		new AutoFocusTextWatcher(targaB, 3);
		new AutoFocusTextWatcher(targaC, 2);
		tipoVeicolo = (Spinner) v.findViewById(R.id.tipoVeicolo);
		regioneResidenza = (Spinner) v.findViewById(R.id.regioneResidenza);
		getActivity().setProgressBarIndeterminateVisibility(false);
		v.findViewById(R.id.ok).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String targa = targaA.getText().toString() + targaB.getText().toString() + targaC.getText().toString();
				String tipo = getResources().getStringArray(R.array.tipoVeicoloKey)[tipoVeicolo.getSelectedItemPosition()];
				String regione = getResources().getStringArray(R.array.regioneResidenzaKey)[regioneResidenza.getSelectedItemPosition()];
				new WebviewDialogFragment().show(getFragmentManager(), bolloUrl + tipo + param1 + regione + param2 + targa, null);
			}
		});
		((MainActivity) getActivity()).sendScreenName(BolloFragment.class.getSimpleName());
		new AdManager().load(getActivity(), ((AdView) v.findViewById(R.id.adView)), true);
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
}
