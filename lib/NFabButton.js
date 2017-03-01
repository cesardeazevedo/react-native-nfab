import React, { Component, PropTypes } from 'react'
import {
  View,
  StyleSheet,
  requireNativeComponent,
  TouchableWithoutFeedback,
} from 'react-native'

class NFabButton extends Component {
  static propTypes = {
    ...View.propTypes,
    ...TouchableWithoutFeedback.propTypes,
    src: PropTypes.string,
    marginBottom: PropTypes.number,
    marginRight: PropTypes.number,
    colorNormal: PropTypes.string,
    colorPressed: PropTypes.string,
    colorRipple: PropTypes.string,
    showShadow: PropTypes.bool,
    shadowColor: PropTypes.string,
    shadowRadius: PropTypes.number,
    shadowXOffset: PropTypes.number,
    shadowYOffset: PropTypes.number,
    size: PropTypes.oneOf(['mini', 'normal']),
    label: PropTypes.string,
    onPress: PropTypes.func,
  };

  static defaultProps = {
    size: 'normal'
  };

  onChange = (e) => {
    const { onPress } = this.props
    onPress && onPress(e)
  }

  render() {
    return (
      <RCTNFabButton
        {...this.props}
        onChange={this.onChange}
      />
    )
  }
}

const RCTNFabButton = requireNativeComponent('RCTNFabButton', NFabButton)

export default NFabButton
