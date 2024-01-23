import { React } from "react";

class Notification extends React.Component {
  constructor(props) {
    super(props);

    this.state = {};
  }

  componentDidMount(){
    console.log(`${this.props.id} "componentDidMount() called.`);
  }

  componentDidUpdate(){
    console.log(`${this.props.id} "componentDidUpdate() called.`);
  }

  componentWillUnmount(){
    console.log(`${this.props.id} "componentWillUnmount() called.`);
  }

  render() {
    return (
      <div className="m-8 p-8 flex-row border border-grey b-16">
        <span className="font-bold text-xl">{this.props.message}</span>
      </div>
    );
  }
}

export default Notification;
