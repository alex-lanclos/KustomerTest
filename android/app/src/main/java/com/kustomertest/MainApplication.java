package com.kustomertest;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.facebook.react.PackageList;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.soloader.SoLoader;
import com.kustomer.core.listeners.KusChatListener;
import com.kustomer.core.models.KusResult;
import com.kustomer.core.models.chat.KusChatMessage;
import com.kustomer.core.models.chat.KusChatMessageDirection;
import com.kustomer.core.models.chat.KusConversation;
import com.kustomer.core.models.chat.KusSatisfaction;
import com.kustomer.core.models.chat.KusTypingIndicator;
import com.kustomer.core.models.chat.KusUser;
import com.kustomer.ui.Kustomer;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

import static android.content.ContentValues.TAG;

public class MainApplication extends Application implements ReactApplication {

    class ExampleChatListener implements KusChatListener {

        public void onChatMessageReceived(String conversationId, KusChatMessage chatMessage) {
            // Handle chat message received for a conversation

            // Check the direction of the message from KusChatMessageDirection and update the UI accordingly
//            if (chatMessage.direction == KusChatMessageDirection.AGENT) {
//                repository.addAgentChatMessage(conversationId, chatMessage)
//            }
        }

        public void onAgentIsTyping(String conversationId, KusTypingIndicator typingIndicator) {
            // Show/Hide typing indicator for an agent for a conversation
        }

        public void onUnreadCountChange(String conversationId, int unreadCount) {
            // Update unread count for a conversation
            // Ignore this if unread count is not shown on UI for every conversation view
            // Ignore this if the unread count callbacks are received for current conversation view

//            if (conversationId != repository.getCurrentActiveConversation()) {
//                repository.updateUnreadCount(conversationId, unreadCount)
//            }
        }

        public void onAgentJoined(String conversationId, KusUser agent) {
            // Update the conversation view with the Agent's avatar/name
        }

        public void onPreviewChanged(String conversationId, String preview) {
            // Update the conversation preview shown on UI
        }

        public void onConversationCreated(KusConversation conversation) {
            // Update the UI when the conversation is created
//            repository.addOrUpdateConversation(conversation)
//            Log.i("test", "conversation created");

//            Function1<? super KusResult<Boolean>, Unit> response = e -> Unit.INSTANCE;
//
//            Map<String, String> conversationDetails = new HashMap<>();
//            conversationDetails.put("someKey", "someValue");
//            Kustomer.Companion.getInstance().describeConversation(conversation.getId(), conversationDetails, response);
        }

        public void onConversationEnded(KusConversation conversation) {
            // Update the UI when the conversation has ended
//            repository.addOrUpdateConversation(conversation)
        }

        public void onConversationUnended(KusConversation conversation) {
            // Update the UI when the conversation is updated by chat assistant
//            repository.addOrUpdateConversation(conversation)
        }

        public void onAssistantEnded(KusConversation conversation) {
            // Update the UI when the conversation is updated by chat assistant
//            repository.addOrUpdateConversation(conversation)
        }

        public void onCustomerMerged(String customerId) {
            // Update the UI for current customer
//            repository.addOrUpdateCustomerId(customerId)
        }

        public void onSatisfactionEventReceived(
                String conversationId,
                KusSatisfaction satisfaction
        ) {
            // Update the UI to show satisfaction feedback form
//            repository.addSatisfactionEvent(satisfaction)
        }
    }

  private final ReactNativeHost mReactNativeHost =
      new ReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
          return BuildConfig.DEBUG;
        }

        @Override
        protected List<ReactPackage> getPackages() {
          @SuppressWarnings("UnnecessaryLocalVariable")
          List<ReactPackage> packages = new PackageList(this).getPackages();
          // Packages that cannot be autolinked yet can be added manually here, for example:
          // packages.add(new MyReactNativePackage());
          return packages;
        }

        @Override
        protected String getJSMainModuleName() {
          return "index";
        }
      };

  @Override
  public ReactNativeHost getReactNativeHost() {
    return mReactNativeHost;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    SoLoader.init(this, /* native exopackage */ false);
    initializeFlipper(this, getReactNativeHost().getReactInstanceManager());

    Function1<? super KusResult<Boolean>, Unit> response = e -> Unit.INSTANCE;

    Kustomer.Companion.init(
            this,
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjVmZTNjOTVlZmQ0OGVhMDA5NmNkNGFjMyIsInVzZXIiOiI1ZmUzYzk1ZDAwYzI5NjIyOTQwYjVjYzIiLCJvcmciOiI1ZjViZTcwZDNjNzUyZjAwMTIyOGYwMzIiLCJvcmdOYW1lIjoid2FpdHItc2FuZGJveCIsInVzZXJUeXBlIjoibWFjaGluZSIsInBvZCI6InByb2QxIiwicm9sZXMiOlsib3JnLnRyYWNraW5nIl0sImF1ZCI6InVybjpjb25zdW1lciIsImlzcyI6InVybjphcGkiLCJzdWIiOiI1ZmUzYzk1ZDAwYzI5NjIyOTQwYjVjYzIifQ.DBFOtsAVgiYybS0O2ux01g2TILhLQKDDCP7slqWYjvw",
             null,
              response
             );



    Kustomer.Companion.getInstance().addChatListener(new ExampleChatListener());
  }

  /**
   * Loads Flipper in React Native templates. Call this in the onCreate method with something like
   * initializeFlipper(this, getReactNativeHost().getReactInstanceManager());
   *
   * @param context
   * @param reactInstanceManager
   */
  private static void initializeFlipper(
      Context context, ReactInstanceManager reactInstanceManager) {
    if (BuildConfig.DEBUG) {
      try {
        /*
         We use reflection here to pick up the class that initializes Flipper,
        since Flipper library is not available in release mode
        */
        Class<?> aClass = Class.forName("com.kustomertest.ReactNativeFlipper");
        aClass
            .getMethod("initializeFlipper", Context.class, ReactInstanceManager.class)
            .invoke(null, context, reactInstanceManager);
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      } catch (NoSuchMethodException e) {
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      } catch (InvocationTargetException e) {
        e.printStackTrace();
      }
    }
  }
}
