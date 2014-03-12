//
//  HelloWorldLayer.m
//  ChartboostApportableDemo
//
//  Created by Vitaliy Ivanov on 3/12/14.
//  Copyright __MyCompanyName__ 2014. All rights reserved.
//

// Import the interfaces
#import "HelloWorldLayer.h"
#import "Chartboost.h"

// HelloWorldLayer implementation
@implementation HelloWorldLayer

+(CCScene *) scene
{
	// 'scene' is an autorelease object.
	CCScene *scene = [CCScene node];
	
	// 'layer' is an autorelease object.
	HelloWorldLayer *layer = [HelloWorldLayer node];
	
	// add layer as a child to scene
	[scene addChild: layer];
	
	// return the scene
	return scene;
}

// on "init" you need to initialize your instance
-(id) init
{
	// always call "super" init
	// Apple recommends to re-assign "self" with the "super" return value
	if( (self=[super init])) {
		
		// create and initialize a Label
		CCLabelTTF *label = [CCLabelTTF labelWithString:@"Touch to see Chartboost Interstitial" fontName:@"Marker Felt" fontSize:16];

		// ask director the the window size
		CGSize size = [[CCDirector sharedDirector] winSize];
	
		// position the label on the center of the screen
		label.position =  ccp( roundf(size.width /2) , roundf(size.height/2) );
		
		self.isTouchEnabled = YES;
		
		// add the label as a child to this Layer
		[self addChild: label];
	}
	return self;
}

- (void)ccTouchesBegan:(NSSet *)touches withEvent:(UIEvent *)event
{
	[[Chartboost sharedChartboost] showInterstitial];
}

// on "dealloc" you need to release all your retained objects
- (void) dealloc
{
	// in case you have something to dealloc, do it in this method
	// in this particular example nothing needs to be released.
	// cocos2d will automatically release all the children (Label)
	
	// don't forget to call "super dealloc"
	[super dealloc];
}
@end
