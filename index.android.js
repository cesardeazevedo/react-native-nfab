import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View,
  Button,
  Platform,
  TextInput,
  Dimensions,
  TouchableNativeFeedback,
} from 'react-native';

import NativeButton from './lib/Button'
import NFabMenu from './lib/NFabMenu'
import NFabButton from './lib/NFabButton'
import RelativeLayout from './lib/RelativeLayout'

const { width, height } = Dimensions.get('window')

const RippleColor = (...args) => (
  Platform.Version >= 21
    ? TouchableNativeFeedback.Ripple(...args)
    : null
)

class nfab extends Component {
  handleOnToggle(e) {
    console.log(e.nativeEvent.opened)
  }

  renderButton() {
    return (
      <TouchableNativeFeedback
        delayPressIn={0}
        delayPressOut={0}
        background={RippleColor('blue')}>
        <View style={styles.button}>
          <Text style={styles.buttonLabel}>{'< Works ---- Doesnt work >'}</Text>
        </View>
      </TouchableNativeFeedback>
    )
  }

  renderRedMenu() {
    const lorem = 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum ultrices .'
    const buttonProp = {
      size:'normal',
      label: lorem,
      colorNormal: '#9b59b6',
      shadowRadius: 4,
      shadowXOffset: 0,
      shadowYOffset: 1,
      shadowColor: '#4D000000'
    }

    return (
      <NFabMenu
        size='normal'
        style={styles.menu}
        alignParentRight
        alignParentBottom
        marginLeft={50}
        marginRight={10}
        marginBottom={10}
        colorNormal='#e74c3c'
        labelsPosition='left'
        onToggle={this.handleOnToggle}>
        <NFabButton {...buttonProp} />
        <NFabButton {...buttonProp} />
        <NFabButton {...buttonProp} />
        <NFabButton {...buttonProp} />
        <NFabButton {...buttonProp} />
      </NFabMenu>
    )
  }

  render() {
    return (
      <RelativeLayout>
        <View style={styles.container}>
          <TextInput style={styles.input} placeholder="Text" />
          <TextInput style={styles.input} placeholder="Text" />
          <TextInput style={styles.input} placeholder="Text" />
          <TextInput style={styles.input} placeholder="Text" />
          <TextInput style={styles.input} placeholder="Text" />
          {this.renderButton()}
          <TextInput style={styles.input} placeholder="Text" />
          <TextInput style={styles.input} placeholder="Text" />
          <NativeButton style={{width, height: 50}} />
        </View>
        {this.renderRedMenu()}
      </RelativeLayout>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    flexDirection: 'column',
    backgroundColor: '#F5FCFF',
  },
  menu: {
    // position: 'absolute',
    // top: 0,
    // left: 0,
    // right: 0,
    // bottom: 0,
    // backgroundColor: 'transparent'
  },
  button: {
    justifyContent: 'center',
    height: 50,
    padding: 8,
    paddingLeft: 24,
    borderColor: 'rgba(0, 0, 0, .2)',
    borderBottomWidth: StyleSheet.hairlineWidth,
  },
  buttonLabel: {
    color: '#000',
  },
  input: {
    height: 46,
    fontSize: 13,
    padding: 4,
  },
});

AppRegistry.registerComponent('nfab', () => nfab);
