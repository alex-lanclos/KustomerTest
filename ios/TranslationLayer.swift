import UIKit
import KustomerChat



@objc public class TranslationLayer: NSObject {
  @objc class func config(withLaunchOptions launchOptions: NSDictionary) {
    class MyListener: KUSChatListener {
       func onChatMessageReceived(conversationId: String,
                                  chatMessage: KUSChatMessage) {
//        print("New message in conversation \(conversationId): \(chatMessage.body)")
      }
      func onConversationCreated(conversationId: String,
                                 conversation: KUSConversation) {
//        print("A new conversation with id = \(conversationId) was created")
      
        
        Kustomer.chatProvider.describeConversation(conversationId: conversationId, attributes: ["favoriteNumberNum":100], {
          result in
          switch result {
            case .success:
              print("ok")
            case .failure(let error):
              print(error)
          }
        })
      }
    }
  
    
    Kustomer.configure(apiKey: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjVmZTNjOTVlZmQ0OGVhMDA5NmNkNGFjMyIsInVzZXIiOiI1ZmUzYzk1ZDAwYzI5NjIyOTQwYjVjYzIiLCJvcmciOiI1ZjViZTcwZDNjNzUyZjAwMTIyOGYwMzIiLCJvcmdOYW1lIjoid2FpdHItc2FuZGJveCIsInVzZXJUeXBlIjoibWFjaGluZSIsInBvZCI6InByb2QxIiwicm9sZXMiOlsib3JnLnRyYWNraW5nIl0sImF1ZCI6InVybjpjb25zdW1lciIsImlzcyI6InVybjphcGkiLCJzdWIiOiI1ZmUzYzk1ZDAwYzI5NjIyOTQwYjVjYzIifQ.DBFOtsAVgiYybS0O2ux01g2TILhLQKDDCP7slqWYjvw", options: nil, launchOptions: launchOptions as? [UIApplication.LaunchOptionsKey : Any])
    
    Kustomer.chatProvider.addChatListener(MyListener())
  }
  
}
