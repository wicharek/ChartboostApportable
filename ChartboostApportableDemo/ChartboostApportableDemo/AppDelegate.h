//
//  AppDelegate.h
//  ChartboostApportableDemo
//
//  Created by Vitaliy Ivanov on 3/12/14.
//  Copyright __MyCompanyName__ 2014. All rights reserved.
//

#import <UIKit/UIKit.h>

@class RootViewController;

@interface AppDelegate : NSObject <UIApplicationDelegate> {
	UIWindow			*window;
	RootViewController	*viewController;
}

@property (nonatomic, retain) UIWindow *window;

@end
