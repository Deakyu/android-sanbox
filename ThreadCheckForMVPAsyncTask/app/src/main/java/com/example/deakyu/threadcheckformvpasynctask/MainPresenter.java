package com.example.deakyu.threadcheckformvpasynctask;

import android.os.AsyncTask;
import android.os.Looper;

import java.lang.ref.WeakReference;
import java.util.Arrays;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;

    @Override
    public void bind(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void unbind() {
        this.view = null;
    }

    @Override
    public void onButtonClicked() {
        new MyTask(this).execute("zxcvbnmasdfgjklqwertyuiop");
    }

    public void onMessageChanged(String message) {
        if(view != null) {
            view.displayMessage(message);
        }
    }

    private static class MyTask extends AsyncTask<String, Void, String> {

        private WeakReference<MainPresenter> presenterWeakReference;

        public MyTask(MainPresenter presenter) {
            presenterWeakReference = new WeakReference<>(presenter);
        }

        @Override
        protected void onPreExecute() {
            System.out.println(MyTask.class.getSimpleName() + " onPreExecute()");
            if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
                System.out.println("MainThread Running");
            } else {
                System.out.println("WorkerThread Running");
            }
        }

        @Override
        protected void onPostExecute(String s) {
            System.out.println(MyTask.class.getSimpleName() + " onPostExecute()");
            if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
                System.out.println("MainThread Running");
            } else {
                System.out.println("WorkerThread Running");
            }

            MainPresenter presenter = presenterWeakReference.get();
            if(presenter == null) return;
            presenter.onMessageChanged(s);
        }

        @Override
        protected String doInBackground(String... strings) {
            System.out.println(MyTask.class.getSimpleName() + " doInBackground()");
            if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
                System.out.println("MainThread Running");
            } else {
                System.out.println("WorkerThread Running");
            }
            String s = strings[0];
            char[] tmp = s.toCharArray();
            Arrays.sort(tmp);
            return new String(tmp);
        }
    }
}
