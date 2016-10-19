package com.medeiros.personal.event;

import com.medeiros.personal.ViewItem;


public abstract class PersonalEvent {

	public static class PersonalUpdateEvent{
		
	}
	
    public static class BrowserResizeEvent {

    }
    
    public static class NotificationsCountUpdatedEvent{
    	
    }
    
    public static class CloseOpenWindowsEvent{
    	
    }
    
    public static class BeanUpdateEvent{
    	
    }

    public static class PostViewChangeEvent{
    
        private final ViewItem view;

        public PostViewChangeEvent(final ViewItem view) {
            this.view = view;
        }

        public ViewItem getView() {
            return view;
        }
    }
}
