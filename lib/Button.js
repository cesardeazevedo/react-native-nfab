import React, { Component, PropTypes } from 'react'
import {
  View,
  StyleSheet,
  requireNativeComponent,
  TouchableWithoutFeedback,
} from 'react-native'

class Button extends Component {
  static propTypes = {
    ...View.propTypes,
    ...TouchableWithoutFeedback.propTypes,
  };

  render() {
    return (
      <RCTButton {...this.props} />
    )
  }
}

const RCTButton = requireNativeComponent('RCTButtonTest', Button)

export default Button
