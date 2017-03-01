import React, { Component, PropTypes } from 'react'
import {
  View,
  findNodeHandle,
  UIManager,
  requireNativeComponent,
  TouchableWithoutFeedback,
} from 'react-native'

class NFabMenu extends Component {
  static propTypes = {
    ...View.propTypes,
    ...TouchableWithoutFeedback.propTypes,
    src: PropTypes.string,
    alignParentRight: PropTypes.bool,
    alignParentBottom: PropTypes.bool,
    marginLeft: PropTypes.number,
    marginRight: PropTypes.number,
    marginBottom: PropTypes.number,
    onToggle: PropTypes.func,
    colorNormal: PropTypes.string,
    colorPressed: PropTypes.string,
    colorRipple: PropTypes.string,
    animationDelayPerItem: PropTypes.number,
    buttonSpacing: PropTypes.number,
    label: PropTypes.string,
    labelsPosition: PropTypes.oneOf(['left', 'right']),
    labelsMargin: PropTypes.number,
    labelsPadding: PropTypes.number,
    labelsPaddingTop: PropTypes.number,
    labelsPaddingLeft: PropTypes.number,
    labelsPaddingRight: PropTypes.number,
    labelsPaddingBottom: PropTypes.number,
    labelsShowShadow: PropTypes.bool,
    labelsSingleLine: PropTypes.bool,
    labelsEllipsize: PropTypes.oneOf(['start', 'middle', 'end', 'marquee']),
    labelsMaxLines: PropTypes.number,
    openDirection: PropTypes.oneOf(['up', 'down']),
    showShadow: PropTypes.bool,
    shadowColor: PropTypes.string,
    shadowRadius: PropTypes.number,
    shadowXOffset: PropTypes.number,
    shadowYOffset: PropTypes.number,
    closeOnTouchOutside: PropTypes.bool,
    size: PropTypes.oneOf(['mini', 'normal']),
  };

  static defaultProps = {
    size: 'normal',
  };

  onToggle = (e) => {
    const { onToggle } = this.props
    onToggle && onToggle(e)
  }

  render() {
    return (
      <RCTNFabMenu
        {...this.props}
        onToggle={this.onToggle}
      />
    )
  }
}

const RCTNFabMenu = requireNativeComponent('RCTNFabMenu', NFabMenu)

export default NFabMenu
