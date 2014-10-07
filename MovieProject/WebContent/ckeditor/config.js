/**
 * @license Copyright (c) 2003-2013, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.html or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	
	config.filebrowserBrowseUrl = '/dukeboard/ckfinder/ckfinder.html';
    config.filebrowserImageBrowseUrl = '/dukeboard/ckfinder/ckfinder.html?type=Images';
    config.filebrowserFlashBrowseUrl = '/dukeboard/ckfinder/ckfinder.html?type=Flash';
    config.filebrowserUploadUrl = '/dukeboard/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files';
    config.filebrowserImageUploadUrl = '/dukeboard/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images';
    config.filebrowserFlashUploadUrl = '/dukeboard/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash'; 
};
