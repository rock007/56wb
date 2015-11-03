/** @jsx React.DOM */

var React = require('react');
var Immutable = require('immutable');

var map1 = Immutable.Map({a:1, b:2, c:3});
var map2 = map1.set('b', 50);
map1.get('b'); // 2
map2.get('b'); // 50

var urlToContent = {
    "new": "What's New",
    groups: "Groups",
    settings: "Settings"
};

var OtherPage = React.createClass({
    propTypes: {
        location: React.PropTypes.array.isRequired
    },
    render: function () {
        var title = urlToContent[this.props.location] || "Other";
        return <h2 className="win-h2" style={{marginLeft: "10px"}}>{title}</h2>
    }
});

module.exports = OtherPage;
