import React, { useState } from 'react';
import './NewDiscussionForm.css';

const NewDiscussionForm = ({ onAddDiscussion }) => {
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  const [customerId, setCustomerId] = useState(''); // Example: could be selected or entered

  const handleSubmit = (event) => {
    event.preventDefault();
    if (title && content && customerId) {
      const newDiscussion = {
        customerId,
        title,
        content,
      };
      onAddDiscussion(newDiscussion);
      setTitle('');
      setContent('');
      setCustomerId('');
    }
  };

  return (
    <div className="new-discussion-form">
      <h4>Create New Discussion</h4>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Customer ID"
          value={customerId}
          onChange={(e) => setCustomerId(e.target.value)}
        />
        <input
          type="text"
          placeholder="Discussion Title"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
        />
        <textarea
          placeholder="Discussion Content"
          value={content}
          onChange={(e) => setContent(e.target.value)}
        />
        <button type="submit">Create Discussion</button>
      </form>
    </div>
  );
};

export default NewDiscussionForm;
