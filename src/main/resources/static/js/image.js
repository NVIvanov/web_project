import React, { Component } from 'react';

class Image extends Component {
    render() {
        // return $('<img ondragstart="handleDragStartInEditor(event)" ondrag="handleDrag(event)" ' +
        //     'ondragend="handleDragEnd(event)" ondragover="handleDragOver(event)" draggable="true" ' +
        //     'style="border: dashed 1px; height: 100px; width: 100px" data-type="IMAGE" id="' + inc++ +
        //     '" src=""/>');
        const style = {border: 'dashed 1px'};
        return <img style={style}/>;
    }
}

const imageSource = {
    beginDrag(props) {
        return {
            componentId: props.id
        }
    }
};