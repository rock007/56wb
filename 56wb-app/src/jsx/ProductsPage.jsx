var React = require('react');
var ReactWinJS = require('react-winjs');

var urlToContent = {
    "new": "What's New",
    groups: "Groups",
    settings: "Settings"
};

var ProductsPage = React.createClass({
    propTypes: {
        location: React.PropTypes.array.isRequired
    },
     itemRenderer: ReactWinJS.reactRenderer(function (item) {
        return <div>{item.data.title}</div>;
    }),
    getInitialState: function () {
        return {
            list: new WinJS.Binding.List([
                { title: "Apple" },
                { title: "Banana" },
                { title: "Grape" },
                { title: "Lemon" },
                { title: "Mint" },
                { title: "Orange" },
                { title: "Pineapple" },
                { title: "Strawberry"}
            ]),
            layout: { type: WinJS.UI.ListLayout }
        };
    },
    render: function () {
         var title = urlToContent[this.props.location] || "Other";
        return (<div>
            <h2 className="win-h2" style={{marginLeft: "10px"}}>{title}</h2>
            <ReactWinJS.ListView
                className="listViewExample win-selectionstylefilled"
                style={{height: "200px"}}
                itemDataSource={this.state.list.dataSource}
                itemTemplate={this.itemRenderer}
                layout={this.state.layout}
                selectionMode="single"
                tapBehavior="directSelect" />

                <div id="basicBindingInputs">
    <ol id="basicBindingTextInputList">
        <li>
            <label for="basicBindingInputText">
                Text:
            </label>
            <input className="win-textbox" type="text" id="basicBindingInputText" />
        </li>
    </ol>
    <ol id="basicBindingColorInputList">
        <li>
            <label for="basicBindingInputRed">
                Red:
            </label>
            <input className="win-textbox" type="text" id="basicBindingInputRed" />
        </li>
        <li>
            <label for="basicBindingInputGreen">
                Green:
            </label>
            <input className="win-textbox" type="text" id="basicBindingInputGreen" />
        </li>
        <li>
            <label for="basicBindingInputBlue">
                Blue:
            </label>
            <input className="win-textbox" type="text" id="basicBindingInputBlue" />
        </li>
    </ol>
</div>
            </div>    
        );
    }
});

module.exports = ProductsPage;
