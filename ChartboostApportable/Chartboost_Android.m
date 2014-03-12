//
//  Chartboost_Android.m
//
//  Created by Vitaliy Ivanov on 2/2/14.
//
//

#import "Chartboost.h"

#ifdef ANDROID

#import <BridgeKit/JavaObject.h>
#import <BridgeKit/AndroidActivity.h>

@interface JavaChartboost : JavaObject

- (id)initWithAppId:(NSString*)appId appSignature:(NSString*)appSignature
	activity:(AndroidActivity*)activity;
- (void)onStart;
- (void)onStop;
- (void)onDestroy;
- (void)cacheInterstitial;
- (void)showInterstitial;

@end

@implementation JavaChartboost

+ (void)initializeJava
{
	[JavaChartboost registerConstructorWithSelector:@selector(initWithAppId:appSignature:activity:)
		arguments:[NSString className], [NSString className], [AndroidActivity className], nil];
	
	[JavaChartboost registerInstanceMethod:@"onStart"
		selector:@selector(onStart)
		arguments:nil];
	
	[JavaChartboost registerInstanceMethod:@"onStop"
		selector:@selector(onStop)
		arguments:nil];
	
	[JavaChartboost registerInstanceMethod:@"onDestroy"
		selector:@selector(onDestroy)
		arguments:nil];
		
	[JavaChartboost registerInstanceMethod:@"cacheInterstitial"
		selector:@selector(cacheInterstitial)
		arguments:nil];
		
	[JavaChartboost registerInstanceMethod:@"showInterstitial"
		selector:@selector(showInterstitial)
		arguments:nil];
}

+ (NSString*)className
{
	return @"com.f17y.ChartboostWrapper";
}

@end

@interface Chartboost()
{
	JavaChartboost* _javaChartboost;
}

@end

@implementation Chartboost

@synthesize appSignature, appId, orientation, timeout, rootView, delegate;

+ (Chartboost*)sharedChartboost
{
	static Chartboost* cb = nil;
	if (!cb)
		cb = [[Chartboost alloc] init];
	return cb;
}

- (void)startSession
{
	if (!_javaChartboost)
	{
		_javaChartboost = [[JavaChartboost alloc] initWithAppId:appId
			appSignature:appSignature
			activity:[AndroidActivity currentActivity]];
	}
	
	[_javaChartboost onStart];
}

- (void)dealloc
{
	[_javaChartboost onDestroy];
}

- (void)stopSession
{
	[_javaChartboost onStop];
}

- (void)cacheInterstitial
{
	[_javaChartboost cacheInterstitial];
}

- (void)cacheInterstitial:(NSString *)location
{
	[_javaChartboost cacheInterstitial];
}

- (void)showInterstitial
{
	[_javaChartboost showInterstitial];
}

- (void)showInterstitial:(NSString *)location
{
	[_javaChartboost showInterstitial];
}

- (BOOL)hasCachedInterstitial
{
	return NO;
}

- (BOOL)hasCachedInterstitial:(NSString *)location
{
	return NO;
}

- (void)cacheMoreApps
{
}

- (void)showMoreApps
{
}

- (BOOL)hasCachedMoreApps
{
	return NO;
}

- (void)dismissChartboostView
{
}

@end
#endif
