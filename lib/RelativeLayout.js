import React, { Component, PropTypes } from 'react'
import {
  View,
  StyleSheet,
  requireNativeComponent,
} from 'react-native'

class RelativeLayout extends Component {
  static propTypes = {
    ...View.propTypes,
  };

  render() {
    return (
      <RCTRelativeLayout
        {...this.props}
        style={this.props.style || styles.container}
      />
    )
  }
}

const RCTRelativeLayout = requireNativeComponent('RCTRelativeLayout', RelativeLayout)

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
})

export default RelativeLayout
