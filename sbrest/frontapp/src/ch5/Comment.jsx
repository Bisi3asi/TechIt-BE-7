function Comment(props) {
    return (
        <div className="flex items-center ml-5 py-2 px-2 my-1 bg-slate-700 border border-white">
            <span className="font-bold mr-2" >{props.name} : </span>
            <span>{props.comment}</span>
        </div>
    );
}

export default Comment;