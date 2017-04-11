import React, { Component } from 'react';
import { DragDropContext } from 'react-dnd';
import HTML5Backend from 'react-dnd-html5-backend';
import ReactDOM from 'react-dom';

export class Editor extends Component {
    render() {
        const style = {border: '1px dashed', width: '80%', height: '500px'};
        return <section><Toolbar/><EditorField/></section>;
    }
}

export class Toolbar extends Component {
    render() {
        return <section className="row show-grid container center-block">
            <SaveButton/>
            <ButtonLabel/>
            <ImageLabel/>
            <PanelLabel/>
            <TextLabel/>
            <TextFieldLabel/>
        </section>
    }
}

function SaveButton() {
    return <section className="col-lg-2 text-center"><button>Сохранить</button></section>
}

function ButtonLabel() {
    return <section className="col-lg-2 text-center h3">Кнопка</section>
}

function ImageLabel() {
    return <section className="col-lg-2 text-center h3">Изображение</section>
}

function PanelLabel() {
    return <section className="col-lg-2 text-center h3">Панель</section>
}

function TextLabel() {
    return <section className="col-lg-2 text-center h3">Текст</section>
}

function TextFieldLabel() {
    return <section className="col-lg-2 text-center h3">Поле ввода</section>
}

class Button extends Component {
    render() {
        //return <button style="border: dashed 1px" ondragstart="handleDragStartInEditor(event)" ' +
        //        'ondrag="handleDrag(event)" ondragend="handleDragEnd(event)" ' +
        //        'ondragover="handleDragOver(event)" draggable="true" data-type="BUTTON" id="' + inc++
        //        +'">Кнопка</button>;
        const style = {border: 'dashed 1px'};
        return <button style={style}>Кнопка</button>;
    }
}

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

class Panel extends Component {
    render() {
        // return $('<section style="height: 100px; border: dashed 1px" ' +
        //     'ondragstart="handleDragStartInEditor(event)" ondrag="handleDrag(event)" ' +
        //     'ondrop="handleDrop(event)" ondragend="handleDragEnd(event)" ondragover="handleDragOver(event)" ' +
        //     'draggable="true" data-type="PANEL" id="' + inc + '">' +
        //     '<button onclick="remove(' + inc++ + ')">Удалить</button></section>');
        const style = {border: '1px dashed', height: '100px'};
        return <section style={style}><button>Удалить</button></section>;
    }
}

class Text extends Component {
    render() {
        // return $('<p ondragstart="handleDragStartInEditor(event)" ondrag="handleDrag(event)" ' +
        //     'ondragend="handleDragEnd(event)" ondragover="handleDragOver(event)" draggable="true" ' +
        //     'style="border: dashed 1px" data-type="TEXT" id="' + inc + '">Lorem ipsum dolor sit amet, ' +
        //     'consectetur adipiscing elit. Fusce interdum semper metus tempus scelerisque. ' +
        //     'Mauris ex nisi, facilisis nec ullamcorper eget, tempus interdum libero.' +
        //     '<button onclick="remove(' + inc++ + ')"></button></p>');
        const style = {border: 'dashed 1px'};
        return <p style={style}>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce interdum semper metus tempus
            scelerisque. Mauris ex nisi, facilisis nec ullamcorper eget, tempus interdum libero.
            <button>Удалить</button>
        </p>;
    }
}

class TextField extends Component {
    render() {
        // return $('<input ondragstart="handleDragStartInEditor(event)" ondrag="handleDrag(event)" ' +
        //     'ondrop="handleDrop(event)" ondragend="handleDragEnd(event)" ' +
        //     'ondragover="handleDragOver(event)" draggable="true" data-type="TEXTFIELD" ' +
        //     'type="text" style="border: dashed 1px" id="' + inc++ + '"/>');
        const style = {border: 'dashed 1px'};
        return <input type="text" style={style}/>;
    }
}

export const ComponentTypes = {
    BUTTON: 'button',
    IMAGE: 'image',
    PANEL: 'panel',
    TEXT: 'text',
    TEXTFIELD: 'textField'
};

////////// called on drag start (?)
const buttonSource = {
    beginDrag(props) {
        return {};
    }
};

const imageSource = {
    beginDrag(props) {
        return {};
    }
};
const panelSource = {
    beginDrag(props) {
        return {};
    }
};
const textSource = {
    beginDrag(props) {
        return {};
    }
};
const textFieldSource = {
    beginDrag(props) {
        return {};
    }
};
////////////

function collect(collect, monitor) {
    return {
        connectDragSource: collect.dragSource(),
        isDragging: monitor.isDragging()
    }
}

const editorTarget = {
    drop(props, monitor) {
        appendComponent(props, monitor.getItem());
    }
};



export default DragDropContext(HTML5Backend)(Editor);

ReactDOM.render(
    <Editor />,
    document.getElementById('editor_container')
);