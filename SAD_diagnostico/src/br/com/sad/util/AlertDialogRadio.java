package br.com.sad.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class AlertDialogRadio extends DialogFragment {

	/**
	 * Declaring the interface, to invoke a callback function in the
	 * implementing activity class
	 */
	AlertPositiveListener alertPositiveListener;

	/**
	 * An interface to be implemented in the hosting activity for "OK" button
	 * click listener
	 */
	public interface AlertPositiveListener {
		public void onPositiveClick(int position);
	}

	/**
	 * This is a callback method executed when this fragment is attached to an
	 * activity. This function ensures that, the hosting activity implements the
	 * interface AlertPositiveListener
	 * */
	public void onAttach(android.app.Activity activity) {
		super.onAttach(activity);
		try {
			alertPositiveListener = (AlertPositiveListener) activity;
		} catch (ClassCastException e) {
			// The hosting activity does not implemented the interface
			// AlertPositiveListener
			throw new ClassCastException(activity.toString()
					+ " must implement AlertPositiveListener");
		}
	}

	/**
	 * This is the OK button listener for the alert dialog, which in turn
	 * invokes the method onPositiveClick(position) of the hosting activity
	 * which is supposed to implement it
	 */
	OnClickListener positiveListener = new OnClickListener() {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			AlertDialog alert = (AlertDialog) dialog;
			int position = alert.getListView().getCheckedItemPosition();
			alertPositiveListener.onPositiveClick(position);
		}
	};

	/**
	 * This is a callback method which will be executed on creating this
	 * fragment
	 */
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		/** Getting the arguments passed to this fragment */
		Bundle bundle = getArguments();

		String sintomas[];
		sintomas = bundle.getStringArray("sintomas");
		
	//	Log.e(""+sintomas[0]+sintomas[1]+sintomas[2]+sintomas[3], "Metodo radio");

		int position = bundle.getInt("position");

		AlertDialog.Builder b = new AlertDialog.Builder(getActivity());
		
		b.setTitle("Escolha um Sintoma ou Sinal");
		
		b.setSingleChoiceItems(sintomas, position, null);
		
		b.setPositiveButton("OK", positiveListener);
		
		b.setNegativeButton("Cancel", null);

		AlertDialog d = b.create();

		return d;
	}
}
