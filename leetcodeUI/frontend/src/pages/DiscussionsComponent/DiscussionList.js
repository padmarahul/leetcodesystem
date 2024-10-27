import React from 'react';
import './DiscussionList.css';

const DiscussionList = ({ discussions }) => {
  return (
    <div className="discussion-list">
      {discussions.map((discussion) => (
        <div key={discussion.id} className="discussion-card">
          <h4 className="discussion-title">{discussion.title}</h4>
          <p className="discussion-content">{discussion.content}</p>
          <div className="discussion-footer">
            <span>Created by: {discussion.customerName}</span>
            <span>{new Date(discussion.createdAt).toLocaleDateString()}</span>
          </div>
        </div>
      ))}
    </div>
  );
};

export default DiscussionList;