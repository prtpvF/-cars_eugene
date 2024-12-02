document.addEventListener('DOMContentLoaded', () => {
  const fileInput = document.getElementById('files');
  const fileNamesDisplay = document.getElementById('fileNames');
  const previewContainer = document.getElementById('filePreview');

  if (fileInput) {
    fileInput.addEventListener('change', () => {
      const fileNames = Array.from(fileInput.files).map(file => file.name).join(', ');
      fileNamesDisplay.textContent = fileNames || 'Файлы не выбраны';

      if (previewContainer) {
        previewContainer.innerHTML = '';
        Array.from(fileInput.files).forEach(file => {
          if (file.type.startsWith('image/')) {
            const img = document.createElement('img');
            img.src = URL.createObjectURL(file);
            img.style.width = '100px';
            img.style.margin = '5px';
            previewContainer.appendChild(img);

            img.onload = () => URL.revokeObjectURL(img.src); // Освобождение памяти
          }
        });
      } else {
        console.error('Element with id "filePreview" not found.');
      }
    });
  } else {
    console.error('Element with id "files" not found.');
  }
});
