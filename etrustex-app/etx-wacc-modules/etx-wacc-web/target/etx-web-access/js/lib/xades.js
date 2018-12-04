var _slicedToArray = function () { function sliceIterator(arr, i) { var _arr = []; var _n = true; var _d = false; var _e = undefined; try { for (var _i = arr[Symbol.iterator](), _s; !(_n = (_s = _i.next()).done); _n = true) { _arr.push(_s.value); if (i && _arr.length === i) break; } } catch (err) { _d = true; _e = err; } finally { try { if (!_n && _i["return"]) _i["return"](); } finally { if (_d) throw _e; } } return _arr; } return function (arr, i) { if (Array.isArray(arr)) { return arr; } else if (Symbol.iterator in Object(arr)) { return sliceIterator(arr, i); } else { throw new TypeError("Invalid attempt to destructure non-iterable instance"); } }; }();

var _get = function get(object, property, receiver) { if (object === null) object = Function.prototype; var desc = Object.getOwnPropertyDescriptor(object, property); if (desc === undefined) { var parent = Object.getPrototypeOf(object); if (parent === null) { return undefined; } else { return get(parent, property, receiver); } } else if ("value" in desc) { return desc.value; } else { var getter = desc.get; if (getter === undefined) { return undefined; } return getter.call(receiver); } };

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

var _typeof = typeof Symbol === "function" && typeof Symbol.iterator === "symbol" ? function (obj) { return typeof obj; } : function (obj) { return obj && typeof Symbol === "function" && obj.constructor === Symbol && obj !== Symbol.prototype ? "symbol" : typeof obj; };

function _toConsumableArray(arr) { if (Array.isArray(arr)) { for (var i = 0, arr2 = Array(arr.length); i < arr.length; i++) { arr2[i] = arr[i]; } return arr2; } else { return Array.from(arr); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

(function (global, factory) {
	(typeof exports === 'undefined' ? 'undefined' : _typeof(exports)) === 'object' && typeof module !== 'undefined' ? factory(exports) : typeof define === 'function' && define.amd ? define(['exports'], factory) : factory(global.XAdES = {});
})(this, function (exports) {
	'use strict';

	var extendStatics = Object.setPrototypeOf || { __proto__: [] } instanceof Array && function (d, b) {
		d.__proto__ = b;
	} || function (d, b) {
		for (var p in b) {
			if (b.hasOwnProperty(p)) d[p] = b[p];
		}
	};

	function __extends(d, b) {
		extendStatics(d, b);
		function __() {
			this.constructor = d;
		}
		d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
	}

	function __decorate(decorators, target, key, desc) {
		var c = arguments.length,
		    r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc,
		    d;
		if ((typeof Reflect === 'undefined' ? 'undefined' : _typeof(Reflect)) === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);else for (var i = decorators.length - 1; i >= 0; i--) {
			if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
		}return c > 3 && r && Object.defineProperty(target, key, r), r;
	}

	function __awaiter(thisArg, _arguments, P, generator) {
		return new (P || (P = Promise))(function (resolve, reject) {
			function fulfilled(value) {
				try {
					step(generator.next(value));
				} catch (e) {
					reject(e);
				}
			}
			function rejected(value) {
				try {
					step(generator["throw"](value));
				} catch (e) {
					reject(e);
				}
			}
			function step(result) {
				result.done ? resolve(result.value) : new P(function (resolve) {
					resolve(result.value);
				}).then(fulfilled, rejected);
			}
			step((generator = generator.apply(thisArg, _arguments || [])).next());
		});
	}

	function __generator(thisArg, body) {
		var _ = { label: 0, sent: function sent() {
				if (t[0] & 1) throw t[1];return t[1];
			}, trys: [], ops: [] },
		    f,
		    y,
		    t,
		    g;
		return g = { next: verb(0), "throw": verb(1), "return": verb(2) }, typeof Symbol === "function" && (g[Symbol.iterator] = function () {
			return this;
		}), g;
		function verb(n) {
			return function (v) {
				return step([n, v]);
			};
		}
		function step(op) {
			if (f) throw new TypeError("Generator is already executing.");
			while (_) {
				try {
					if (f = 1, y && (t = y[op[0] & 2 ? "return" : op[0] ? "throw" : "next"]) && !(t = t.call(y, op[1])).done) return t;
					if (y = 0, t) op = [0, t.value];
					switch (op[0]) {
						case 0:case 1:
							t = op;break;
						case 4:
							_.label++;return { value: op[1], done: false };
						case 5:
							_.label++;y = op[1];op = [0];continue;
						case 7:
							op = _.ops.pop();_.trys.pop();continue;
						default:
							if (!(t = _.trys, t = t.length > 0 && t[t.length - 1]) && (op[0] === 6 || op[0] === 2)) {
								_ = 0;continue;
							}
							if (op[0] === 3 && (!t || op[1] > t[0] && op[1] < t[3])) {
								_.label = op[1];break;
							}
							if (op[0] === 6 && _.label < t[1]) {
								_.label = t[1];t = op;break;
							}
							if (t && _.label < t[2]) {
								_.label = t[2];_.ops.push(op);break;
							}
							if (t[2]) _.ops.pop();
							_.trys.pop();continue;
					}
					op = body.call(thisArg, _);
				} catch (e) {
					op = [6, e];y = 0;
				} finally {
					f = t = 0;
				}
			}if (op[0] & 5) throw op[1];return { value: op[0] ? op[1] : void 0, done: true };
		}
	}

	var ELEMENT = "element";
	var ATTRIBUTE = "attribute";
	var CONTENT = "content";

	var MAX = 1e9;
	function assign(target) {
		for (var _len = arguments.length, sources = Array(_len > 1 ? _len - 1 : 0), _key = 1; _key < _len; _key++) {
			sources[_key - 1] = arguments[_key];
		}

		var res = arguments[0];
		for (var i = 1; i < arguments.length; i++) {
			var obj = arguments[i];
			for (var prop in obj) {
				if (!obj.hasOwnProperty(prop)) {
					continue;
				}
				res[prop] = obj[prop];
			}
		}
		return res;
	}
	function XmlElement(params) {
		return function (target) {
			var t = target;
			t.localName = params.localName || t.name;
			t.namespaceURI = params.namespaceURI || t.namespaceURI || null;
			t.prefix = params.prefix || t.prefix || null;
			t.parser = params.parser || t.parser;
			if (t.target !== t) {
				t.items = assign({}, t.items);
			}
			t.target = target;
		};
	}
	function XmlChildElement() {
		var params = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

		return function (target, propertyKey) {
			var t = target.constructor;
			var key = propertyKey;
			if (!t.items) {
				t.items = {};
			}
			if (t.target !== t) {
				t.items = assign({}, t.items);
			}
			t.target = target;
			if (params.parser) {
				t.items[key] = {
					parser: params.parser,
					required: params.required || false,
					maxOccurs: params.maxOccurs || MAX,
					minOccurs: params.minOccurs === void 0 ? 0 : params.minOccurs,
					noRoot: params.noRoot || false
				};
			} else {
				t.items[key] = {
					namespaceURI: params.namespaceURI || null,
					required: params.required || false,
					prefix: params.prefix || null,
					defaultValue: params.defaultValue,
					converter: params.converter
				};
			}
			params.localName = params.localName || params.parser && params.parser.localName || key;
			t.items[key].namespaceURI = params.namespaceURI || params.parser && params.parser.namespaceURI || null;
			t.items[key].prefix = params.prefix || params.parser && params.parser.prefix || null;
			t.items[key].localName = params.localName;
			t.items[key].type = ELEMENT;
			defineProperty(target, key, params);
		};
	}
	function XmlAttribute() {
		var params = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : { required: false, namespaceURI: null };

		return function (target, propertyKey) {
			var t = target.constructor;
			var key = propertyKey;
			if (!params.localName) {
				params.localName = propertyKey;
			}
			if (!t.items) {
				t.items = {};
			}
			if (t.target !== t) {
				t.items = assign({}, t.items);
			}
			t.target = target;
			t.items[propertyKey] = params;
			t.items[propertyKey].type = ATTRIBUTE;
			defineProperty(target, key, params);
		};
	}
	function XmlContent() {
		var params = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : { required: false };

		return function (target, propertyKey) {
			var t = target.constructor;
			var key = propertyKey;
			if (!t.items) {
				t.items = {};
			}
			if (t.target !== t) {
				t.items = assign({}, t.items);
			}
			t.target = target;
			t.items[propertyKey] = params;
			t.items[propertyKey].type = CONTENT;
			defineProperty(target, key, params);
		};
	}
	function defineProperty(target, key, params) {
		var key2 = '_' + key;
		var opt = {
			set: function set(v) {
				if (this[key2] !== v) {
					this.element = null;
					this[key2] = v;
				}
			},
			get: function get() {
				if (this[key2] === void 0) {
					var defaultValue = params.defaultValue;
					if (params.parser) {
						defaultValue = new params.parser();
						defaultValue.localName = params.localName;
					}
					this[key2] = defaultValue;
				}
				return this[key2];
			}
		};
		Object.defineProperty(target, key2, { writable: true, enumerable: false });
		Object.defineProperty(target, key, opt);
	}

	var Collection = function () {
		function Collection(items) {
			_classCallCheck(this, Collection);

			this.items = new Array();
			if (items) {
				this.items = items;
			}
		}

		_createClass(Collection, [{
			key: 'Item',
			value: function Item(index) {
				return this.items[index] || null;
			}
		}, {
			key: 'Add',
			value: function Add(item) {
				this.items.push(item);
			}
		}, {
			key: 'Pop',
			value: function Pop() {
				return this.items.pop();
			}
		}, {
			key: 'RemoveAt',
			value: function RemoveAt(index) {
				this.items = this.items.filter(function (item, index2) {
					return index2 !== index;
				});
			}
		}, {
			key: 'Clear',
			value: function Clear() {
				this.items = new Array();
			}
		}, {
			key: 'GetIterator',
			value: function GetIterator() {
				return this.items;
			}
		}, {
			key: 'ForEach',
			value: function ForEach(cb) {
				this.GetIterator().forEach(cb);
			}
		}, {
			key: 'Map',
			value: function Map(cb) {
				return new Collection(this.GetIterator().map(cb));
			}
		}, {
			key: 'Filter',
			value: function Filter(cb) {
				return new Collection(this.GetIterator().filter(cb));
			}
		}, {
			key: 'Sort',
			value: function Sort(cb) {
				return new Collection(this.GetIterator().sort(cb));
			}
		}, {
			key: 'Every',
			value: function Every(cb) {
				return this.GetIterator().every(cb);
			}
		}, {
			key: 'Some',
			value: function Some(cb) {
				return this.GetIterator().some(cb);
			}
		}, {
			key: 'IsEmpty',
			value: function IsEmpty() {
				return this.Count === 0;
			}
		}, {
			key: 'Count',
			get: function get() {
				return this.items.length;
			}
		}]);

		return Collection;
	}();

	function printf(text) {
		for (var _len2 = arguments.length, args = Array(_len2 > 1 ? _len2 - 1 : 0), _key2 = 1; _key2 < _len2; _key2++) {
			args[_key2 - 1] = arguments[_key2];
		}

		var msg = text;
		var regFind = /[^%](%\d+)/g;
		var match = null;
		var matches = [];
		while (match = regFind.exec(msg)) {
			matches.push({ arg: match[1], index: match.index });
		}
		for (var i = matches.length - 1; i >= 0; i--) {
			var item = matches[i];
			var arg = item.arg.substring(1);
			var index = item.index + 1;
			msg = msg.substring(0, index) + arguments[+arg] + msg.substring(index + 1 + arg.length);
		}
		msg = msg.replace("%%", "%");
		return msg;
	}
	function padNum(num, size) {
		var s = num + "";
		while (s.length < size) {
			s = "0" + s;
		}
		return s;
	}

	var XmlError = function XmlError(code) {
		for (var _len3 = arguments.length, args = Array(_len3 > 1 ? _len3 - 1 : 0), _key3 = 1; _key3 < _len3; _key3++) {
			args[_key3 - 1] = arguments[_key3];
		}

		_classCallCheck(this, XmlError);

		this.prefix = "XMLJS";
		this.code = code;
		this.name = this.constructor.name;
		arguments[0] = xes[code];
		var message = printf.apply(this, arguments);
		this.message = '' + this.prefix + padNum(code, 4) + ': ' + message;
		this.stack = new Error(this.message).stack;
	};

	var XE;
	(function (XE) {
		XE[XE["NONE"] = 0] = "NONE";
		XE[XE["NULL_REFERENCE"] = 1] = "NULL_REFERENCE";
		XE[XE["NULL_PARAM"] = 2] = "NULL_PARAM";
		XE[XE["DECORATOR_NULL_PARAM"] = 3] = "DECORATOR_NULL_PARAM";
		XE[XE["COLLECTION_LIMIT"] = 4] = "COLLECTION_LIMIT";
		XE[XE["METHOD_NOT_IMPLEMENTED"] = 5] = "METHOD_NOT_IMPLEMENTED";
		XE[XE["METHOD_NOT_SUPPORTED"] = 6] = "METHOD_NOT_SUPPORTED";
		XE[XE["PARAM_REQUIRED"] = 7] = "PARAM_REQUIRED";
		XE[XE["CONVERTER_UNSUPPORTED"] = 8] = "CONVERTER_UNSUPPORTED";
		XE[XE["ELEMENT_MALFORMED"] = 9] = "ELEMENT_MALFORMED";
		XE[XE["ELEMENT_MISSING"] = 10] = "ELEMENT_MISSING";
		XE[XE["ATTRIBUTE_MISSING"] = 11] = "ATTRIBUTE_MISSING";
		XE[XE["CONTENT_MISSING"] = 12] = "CONTENT_MISSING";
		XE[XE["CRYPTOGRAPHIC"] = 13] = "CRYPTOGRAPHIC";
		XE[XE["CRYPTOGRAPHIC_NO_MODULE"] = 14] = "CRYPTOGRAPHIC_NO_MODULE";
		XE[XE["CRYPTOGRAPHIC_UNKNOWN_TRANSFORM"] = 15] = "CRYPTOGRAPHIC_UNKNOWN_TRANSFORM";
		XE[XE["ALGORITHM_NOT_SUPPORTED"] = 16] = "ALGORITHM_NOT_SUPPORTED";
		XE[XE["ALGORITHM_WRONG_NAME"] = 17] = "ALGORITHM_WRONG_NAME";
		XE[XE["XML_EXCEPTION"] = 18] = "XML_EXCEPTION";
	})(XE || (XE = {}));
	var xes = {};
	xes[XE.NONE] = "No decription";
	xes[XE.NULL_REFERENCE] = "Null reference";
	xes[XE.NULL_PARAM] = "'%1' has empty '%2' object";
	xes[XE.DECORATOR_NULL_PARAM] = "Decorator '%1' has empty '%2' parameter";
	xes[XE.COLLECTION_LIMIT] = "Collection of '%1' in element '%2' has wrong amount of items";
	xes[XE.METHOD_NOT_IMPLEMENTED] = "Method is not implemented";
	xes[XE.METHOD_NOT_SUPPORTED] = "Method is not supported";
	xes[XE.PARAM_REQUIRED] = "Required parameter is missing '%1'";
	xes[XE.CONVERTER_UNSUPPORTED] = "Converter is not supported";
	xes[XE.ELEMENT_MALFORMED] = "Malformed element '%1'";
	xes[XE.ELEMENT_MISSING] = "Element '%1' is missing in '%2'";
	xes[XE.ATTRIBUTE_MISSING] = "Attribute '%1' is missing in '%2'";
	xes[XE.CONTENT_MISSING] = "Content is missing in '%1'";
	xes[XE.CRYPTOGRAPHIC] = "Cryptographic error: %1";
	xes[XE.CRYPTOGRAPHIC_NO_MODULE] = "WebCrypto module is not found";
	xes[XE.CRYPTOGRAPHIC_UNKNOWN_TRANSFORM] = "Unknown transform %1";
	xes[XE.ALGORITHM_NOT_SUPPORTED] = "Algorithm is not supported '%1'";
	xes[XE.ALGORITHM_WRONG_NAME] = "Algorithm wrong name in use '%1'";
	xes[XE.XML_EXCEPTION] = "XML exception: %1";

	var Convert = function () {
		function Convert() {
			_classCallCheck(this, Convert);
		}

		_createClass(Convert, null, [{
			key: 'ToString',
			value: function ToString(buffer) {
				var enc = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : "utf8";

				var buf = new Uint8Array(buffer);
				switch (enc.toLowerCase()) {
					case "utf8":
						return this.ToUtf8String(buf);
					case "binary":
						return this.ToBinary(buf);
					case "hex":
						return this.ToHex(buf);
					case "base64":
						return this.ToBase64(buf);
					case "base64url":
						return this.ToBase64Url(buf);
					default:
						throw new XmlError(XE.CONVERTER_UNSUPPORTED);
				}
			}
		}, {
			key: 'FromString',
			value: function FromString(str) {
				var enc = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : "utf8";

				switch (enc.toLowerCase()) {
					case "utf8":
						return this.FromUtf8String(str);
					case "binary":
						return this.FromBinary(str);
					case "hex":
						return this.FromHex(str);
					case "base64":
						return this.FromBase64(str);
					case "base64url":
						return this.FromBase64Url(str);
					default:
						throw new XmlError(XE.CONVERTER_UNSUPPORTED);
				}
			}
		}, {
			key: 'ToBase64',
			value: function ToBase64(buf) {
				if (typeof btoa !== "undefined") {
					var binary = this.ToString(buf, "binary");
					return btoa(binary);
				} else if (typeof Buffer !== "undefined") {
					return new Buffer(buf).toString("base64");
				} else {
					throw new XmlError(XE.CONVERTER_UNSUPPORTED);
				}
			}
		}, {
			key: 'FromBase64',
			value: function FromBase64(base64Text) {
				base64Text = base64Text.replace(/\n/g, "").replace(/\r/g, "").replace(/\t/g, "").replace(/\s/g, "");
				if (typeof atob !== "undefined") {
					return this.FromBinary(atob(base64Text));
				} else if (typeof Buffer !== "undefined") {
					return new Buffer(base64Text, "base64");
				} else {
					throw new XmlError(XE.CONVERTER_UNSUPPORTED);
				}
			}
		}, {
			key: 'FromBase64Url',
			value: function FromBase64Url(base64url) {
				return this.FromBase64(this.Base64Padding(base64url.replace(/\-/g, "+").replace(/\_/g, "/")));
			}
		}, {
			key: 'ToBase64Url',
			value: function ToBase64Url(data) {
				return this.ToBase64(data).replace(/\+/g, "-").replace(/\//g, "_").replace(/\=/g, "");
			}
		}, {
			key: 'FromUtf8String',
			value: function FromUtf8String(text) {
				var s = unescape(encodeURIComponent(text));
				var uintArray = new Uint8Array(s.length);
				for (var i = 0; i < s.length; i++) {
					uintArray[i] = s.charCodeAt(i);
				}
				return uintArray;
			}
		}, {
			key: 'ToUtf8String',
			value: function ToUtf8String(buffer) {
				var encodedString = String.fromCharCode.apply(null, buffer);
				var decodedString = decodeURIComponent(escape(encodedString));
				return decodedString;
			}
		}, {
			key: 'FromBinary',
			value: function FromBinary(text) {
				var stringLength = text.length;
				var resultView = new Uint8Array(stringLength);
				for (var i = 0; i < stringLength; i++) {
					resultView[i] = text.charCodeAt(i);
				}
				return resultView;
			}
		}, {
			key: 'ToBinary',
			value: function ToBinary(buffer) {
				var resultString = "";
				for (var i = 0; i < buffer.length; i++) {
					resultString = resultString + String.fromCharCode(buffer[i]);
				}
				return resultString;
			}
		}, {
			key: 'ToHex',
			value: function ToHex(buffer) {
				var splitter = "";
				var res = [];
				for (var i = 0; i < buffer.length; i++) {
					var char = buffer[i].toString(16);
					res.push(char.length === 1 ? "0" + char : char);
				}
				return res.join(splitter);
			}
		}, {
			key: 'FromHex',
			value: function FromHex(hexString) {
				var res = new Uint8Array(hexString.length / 2);
				for (var i = 0; i < hexString.length; i = i + 2) {
					var c = hexString.slice(i, i + 2);
					res[i / 2] = parseInt(c, 16);
				}
				return res;
			}
		}, {
			key: 'ToDateTime',
			value: function ToDateTime(dateTime) {
				return new Date(dateTime);
			}
		}, {
			key: 'FromDateTime',
			value: function FromDateTime(dateTime) {
				var str = dateTime.toISOString();
				return str;
			}
		}, {
			key: 'Base64Padding',
			value: function Base64Padding(base64) {
				var padCount = 4 - base64.length % 4;
				if (padCount < 4) {
					for (var i = 0; i < padCount; i++) {
						base64 += "=";
					}
				}
				return base64;
			}
		}]);

		return Convert;
	}();

	var APPLICATION_XML = "application/xml";
	var XmlNodeType;
	(function (XmlNodeType) {
		XmlNodeType[XmlNodeType["None"] = 0] = "None";
		XmlNodeType[XmlNodeType["Element"] = 1] = "Element";
		XmlNodeType[XmlNodeType["Attribute"] = 2] = "Attribute";
		XmlNodeType[XmlNodeType["Text"] = 3] = "Text";
		XmlNodeType[XmlNodeType["CDATA"] = 4] = "CDATA";
		XmlNodeType[XmlNodeType["EntityReference"] = 5] = "EntityReference";
		XmlNodeType[XmlNodeType["Entity"] = 6] = "Entity";
		XmlNodeType[XmlNodeType["ProcessingInstruction"] = 7] = "ProcessingInstruction";
		XmlNodeType[XmlNodeType["Comment"] = 8] = "Comment";
		XmlNodeType[XmlNodeType["Document"] = 9] = "Document";
		XmlNodeType[XmlNodeType["DocumentType"] = 10] = "DocumentType";
		XmlNodeType[XmlNodeType["DocumentFragment"] = 11] = "DocumentFragment";
		XmlNodeType[XmlNodeType["Notation"] = 12] = "Notation";
		XmlNodeType[XmlNodeType["Whitespace"] = 13] = "Whitespace";
		XmlNodeType[XmlNodeType["SignificantWhitespace"] = 14] = "SignificantWhitespace";
		XmlNodeType[XmlNodeType["EndElement"] = 15] = "EndElement";
		XmlNodeType[XmlNodeType["EndEntity"] = 16] = "EndEntity";
		XmlNodeType[XmlNodeType["XmlDeclaration"] = 17] = "XmlDeclaration";
	})(XmlNodeType || (XmlNodeType = {}));

	var xpath = function xpath(node, xPath) {
		throw new Error("Not implemented");
	};
	var sWindow = void 0;
	if (typeof self === "undefined") {
		sWindow = global;
		var xmldom = require("xmldom-alpha");
		xpath = require("xpath.js");
		sWindow.XMLSerializer = xmldom.XMLSerializer;
		sWindow.DOMParser = xmldom.DOMParser;
		sWindow.DOMImplementation = xmldom.DOMImplementation;
		sWindow.document = new DOMImplementation().createDocument("http://www.w3.org/1999/xhtml", "html", null);
	} else {
		sWindow = self;
	}
	function SelectNodesEx(node, xPath) {
		var doc = node.ownerDocument == null ? node : node.ownerDocument;
		var nsResolver = document.createNSResolver(node.ownerDocument == null ? node.documentElement : node.ownerDocument.documentElement);
		var personIterator = doc.evaluate(xPath, node, nsResolver, XPathResult.ANY_TYPE, null);
		var ns = [];
		var n = void 0;
		while (n = personIterator.iterateNext()) {
			ns.push(n);
		}
		return ns;
	}
	var Select = typeof self !== "undefined" ? SelectNodesEx : xpath;
	function Parse(xmlString) {
		xmlString = xmlString.replace(/\r\n/g, "\n").replace(/\r/g, "\n");
		return new DOMParser().parseFromString(xmlString, APPLICATION_XML);
	}
	function SelectSingleNode(node, path) {
		var ns = Select(node, path);
		if (ns && ns.length > 0) {
			return ns[0];
		}
		return null;
	}
	function _SelectNamespaces(node) {
		var selectedNodes = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : {};

		if (node && node.nodeType === XmlNodeType.Element) {
			var el = node;
			if (el.namespaceURI && el.namespaceURI !== "http://www.w3.org/XML/1998/namespace" && !selectedNodes[el.prefix || ""]) {
				selectedNodes[el.prefix ? el.prefix : ""] = node.namespaceURI;
			}
			for (var i = 0; i < node.childNodes.length; i++) {
				var childNode = node.childNodes.item(i);
				if (childNode && childNode.nodeType === XmlNodeType.Element) {
					_SelectNamespaces(childNode, selectedNodes);
				}
			}
		}
	}
	function SelectNamespaces(node) {
		var attrs = {};
		_SelectNamespaces(node, attrs);
		return attrs;
	}
	function assign$1(target) {
		for (var _len4 = arguments.length, sources = Array(_len4 > 1 ? _len4 - 1 : 0), _key4 = 1; _key4 < _len4; _key4++) {
			sources[_key4 - 1] = arguments[_key4];
		}

		var res = arguments[0];
		for (var i = 1; i < arguments.length; i++) {
			var obj = arguments[i];
			for (var prop in obj) {
				if (!obj.hasOwnProperty(prop)) {
					continue;
				}
				res[prop] = obj[prop];
			}
		}
		return res;
	}

	var XmlBase64Converter = {
		get: function get(value) {
			if (value) {
				return Convert.ToBase64(value);
			}
			return void 0;
		},
		set: function set(value) {
			return Convert.FromBase64(value);
		}
	};
	var XmlNumberConverter = {
		get: function get(value) {
			if (value) {
				return value.toString();
			}
			return "0";
		},
		set: function set(value) {
			return Number(value);
		}
	};
	var DEFAULT_ROOT_NAME = "xml_root";

	var XmlObject = function () {
		function XmlObject() {
			_classCallCheck(this, XmlObject);

			this.prefix = this.GetStatic().prefix || null;
			this.localName = this.GetStatic().localName;
			this.namespaceURI = this.GetStatic().namespaceURI;
		}

		_createClass(XmlObject, [{
			key: 'HasChanged',
			value: function HasChanged() {
				var self = this.GetStatic();
				if (self.items) {
					for (var key in self.items) {
						if (!self.items.hasOwnProperty(key)) {
							continue;
						}
						var item = self.items[key];
						var value = this[key];
						if (item.parser && value && value.HasChanged()) {
							return true;
						}
					}
				}
				return this.element === null;
			}
		}, {
			key: 'GetXml',
			value: function GetXml(hard) {
				if (!(hard || this.HasChanged())) {
					return this.element || null;
				}
				var doc = this.CreateDocument();
				var el = this.CreateElement();
				var self = this.GetStatic();
				var localName = this.localName;
				if (self.items) {
					for (var key in self.items) {
						if (!self.items.hasOwnProperty(key)) {
							continue;
						}
						var parser = this[key];
						var selfItem = self.items[key];
						switch (selfItem.type) {
							case CONTENT:
								{
									var schema = selfItem;
									var value = schema.converter ? schema.converter.get(parser) : parser;
									if (schema.required && (value === null || value === void 0)) {
										throw new XmlError(XE.CONTENT_MISSING, localName);
									}
									if (schema.defaultValue !== parser || schema.required) {
										el.textContent = value;
									}
									break;
								}
							case ATTRIBUTE:
								{
									var _schema = selfItem;
									var _value2 = _schema.converter ? _schema.converter.get(parser) : parser;
									if (_schema.required && (_value2 === null || _value2 === void 0)) {
										throw new XmlError(XE.ATTRIBUTE_MISSING, _schema.localName, localName);
									}
									if (_schema.defaultValue !== parser || _schema.required) {
										if (!_schema.namespaceURI) {
											el.setAttribute(_schema.localName, _value2);
										} else {
											el.setAttributeNS(_schema.namespaceURI, _schema.localName, _value2);
										}
									}
									break;
								}
							case ELEMENT:
								{
									var _schema2 = selfItem;
									var node = null;
									if (_schema2.parser) {
										if (_schema2.required && !parser || _schema2.minOccurs && !parser.Count) {
											throw new XmlError(XE.ELEMENT_MISSING, parser.localName, localName);
										}
										if (parser) {
											node = parser.GetXml(parser.element === void 0 && (_schema2.required || parser.Count));
										}
									} else {
										var _value3 = _schema2.converter ? _schema2.converter.get(parser) : parser;
										if (_schema2.required && _value3 === void 0) {
											throw new XmlError(XE.ELEMENT_MISSING, _schema2.localName, localName);
										}
										if (parser !== _schema2.defaultValue || _schema2.required) {
											if (!_schema2.namespaceURI) {
												node = doc.createElement('' + (_schema2.prefix ? _schema2.prefix + ":" : "") + _schema2.localName);
											} else {
												node = doc.createElementNS(_schema2.namespaceURI, '' + (_schema2.prefix ? _schema2.prefix + ":" : "") + _schema2.localName);
											}
											node.textContent = _value3;
										}
									}
									if (node) {
										if (_schema2.noRoot) {
											var els = [];
											for (var i = 0; i < node.childNodes.length; i++) {
												var colNode = node.childNodes.item(i);
												if (colNode.nodeType === XmlNodeType.Element) {
													els.push(colNode);
												}
											}
											if (els.length < _schema2.minOccurs || els.length > _schema2.maxOccurs) {
												throw new XmlError(XE.COLLECTION_LIMIT, parser.localName, self.localName);
											}
											els.forEach(function (e) {
												return el.appendChild(e.cloneNode(true));
											});
										} else if (node.childNodes.length < _schema2.minOccurs || node.childNodes.length > _schema2.maxOccurs) {
											throw new XmlError(XE.COLLECTION_LIMIT, parser.localName, self.localName);
										} else {
											el.appendChild(node);
										}
									}
									break;
								}
						}
					}
				}
				this.OnGetXml(el);
				this.element = el;
				return el;
			}
		}, {
			key: 'LoadXml',
			value: function LoadXml(param) {
				var element = void 0;
				if (typeof param === "string") {
					var doc = Parse(param);
					element = doc.documentElement;
				} else {
					element = param;
				}
				if (!element) {
					throw new XmlError(XE.PARAM_REQUIRED, "element");
				}
				var self = this.GetStatic();
				var localName = this.localName;
				if (!(element.localName === localName && element.namespaceURI == this.NamespaceURI)) {
					throw new XmlError(XE.ELEMENT_MALFORMED, localName);
				}
				if (self.items) {
					for (var key in self.items) {
						if (!self.items.hasOwnProperty(key)) {
							continue;
						}
						var selfItem = self.items[key];
						switch (selfItem.type) {
							case CONTENT:
								{
									var schema = selfItem;
									if (schema.required && !element.textContent) {
										throw new XmlError(XE.CONTENT_MISSING, localName);
									}
									if (!element.textContent) {
										this[key] = schema.defaultValue;
									} else {
										var value = schema.converter ? schema.converter.set(element.textContent) : element.textContent;
										this[key] = value;
									}
									break;
								}
							case ATTRIBUTE:
								{
									var _schema3 = selfItem;
									var hasAttribute = void 0;
									var getAttribute = void 0;
									if (_schema3.namespaceURI) {
										hasAttribute = element.hasAttributeNS.bind(element, _schema3.namespaceURI, _schema3.localName);
										getAttribute = element.getAttributeNS.bind(element, _schema3.namespaceURI, _schema3.localName);
									} else {
										hasAttribute = element.hasAttribute.bind(element, _schema3.localName);
										getAttribute = element.getAttribute.bind(element, _schema3.localName);
									}
									if (_schema3.required && !hasAttribute()) {
										throw new XmlError(XE.ATTRIBUTE_MISSING, _schema3.localName, localName);
									}
									if (!hasAttribute()) {
										this[key] = _schema3.defaultValue;
									} else {
										var _value4 = _schema3.converter ? _schema3.converter.set(getAttribute()) : getAttribute();
										this[key] = _value4;
									}
									break;
								}
							case ELEMENT:
								{
									var _schema4 = selfItem;
									if (_schema4.noRoot) {
										if (!_schema4.parser) {
											throw new XmlError(XE.XML_EXCEPTION, 'Schema for \'' + _schema4.localName + '\' with flag noRoot must have \'parser\'');
										}
										var col = new _schema4.parser();
										if (!(col instanceof XmlCollection)) {
											throw new XmlError(XE.XML_EXCEPTION, 'Schema for \'' + _schema4.localName + '\' with flag noRoot must have \'parser\' like instance of XmlCollection');
										}
										col.OnLoadXml(element);
										delete col.element;
										if (col.Count < _schema4.minOccurs || col.Count > _schema4.maxOccurs) {
											throw new XmlError(XE.COLLECTION_LIMIT, _schema4.parser.localName, localName);
										}
										this[key] = col;
										continue;
									}
									var foundElement = null;
									for (var i = 0; i < element.childNodes.length; i++) {
										var node = element.childNodes.item(i);
										if (node.nodeType !== XmlNodeType.Element) {
											continue;
										}
										var el = node;
										if (el.localName === _schema4.localName && el.namespaceURI == _schema4.namespaceURI) {
											foundElement = el;
											break;
										}
									}
									if (_schema4.required && !foundElement) {
										throw new XmlError(XE.ELEMENT_MISSING, _schema4.parser ? _schema4.parser.localName : _schema4.localName, localName);
									}
									if (!_schema4.parser) {
										if (!foundElement) {
											this[key] = _schema4.defaultValue;
										} else {
											var _value5 = _schema4.converter ? _schema4.converter.set(foundElement.textContent) : foundElement.textContent;
											this[key] = _value5;
										}
									} else {
										if (foundElement) {
											var _value6 = new _schema4.parser();
											_value6.localName = _schema4.localName;
											_value6.namespaceURI = _schema4.namespaceURI;
											this[key] = _value6;
											_value6.LoadXml(foundElement);
										}
									}
									break;
								}
						}
					}
				}
				this.OnLoadXml(element);
				this.prefix = element.prefix || "";
				this.element = element;
			}
		}, {
			key: 'toString',
			value: function toString() {
				var xml = this.GetXml();
				return xml ? new XMLSerializer().serializeToString(xml) : "";
			}
		}, {
			key: 'GetElement',
			value: function GetElement(name) {
				var required = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : true;

				if (!this.element) {
					throw new XmlError(XE.NULL_PARAM, this.localName);
				}
				return XmlObject.GetElement(this.element, name, required);
			}
		}, {
			key: 'GetChildren',
			value: function GetChildren(localName, nameSpace) {
				if (!this.element) {
					throw new XmlError(XE.NULL_PARAM, this.localName);
				}
				return XmlObject.GetChildren(this.element, localName, nameSpace || this.NamespaceURI || undefined);
			}
		}, {
			key: 'GetChild',
			value: function GetChild(localName) {
				var required = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : true;

				if (!this.element) {
					throw new XmlError(XE.NULL_PARAM, this.localName);
				}
				return XmlObject.GetChild(this.element, localName, this.NamespaceURI || undefined, required);
			}
		}, {
			key: 'GetFirstChild',
			value: function GetFirstChild(localName, namespace) {
				if (!this.element) {
					throw new XmlError(XE.NULL_PARAM, this.localName);
				}
				return XmlObject.GetFirstChild(this.element, localName, namespace);
			}
		}, {
			key: 'GetAttribute',
			value: function GetAttribute(name, defaultValue) {
				var required = arguments.length > 2 && arguments[2] !== undefined ? arguments[2] : true;

				if (!this.element) {
					throw new XmlError(XE.NULL_PARAM, this.localName);
				}
				return XmlObject.GetAttribute(this.element, name, defaultValue, required);
			}
		}, {
			key: 'IsEmpty',
			value: function IsEmpty() {
				return this.Element === void 0;
			}
		}, {
			key: 'OnLoadXml',
			value: function OnLoadXml(element) {}
		}, {
			key: 'GetStatic',
			value: function GetStatic() {
				return this.constructor;
			}
		}, {
			key: 'GetPrefix',
			value: function GetPrefix() {
				return this.Prefix ? this.prefix + ":" : "";
			}
		}, {
			key: 'OnGetXml',
			value: function OnGetXml(element) {}
		}, {
			key: 'CreateElement',
			value: function CreateElement(document, localName) {
				var namespaceUri = arguments.length > 2 && arguments[2] !== undefined ? arguments[2] : null;
				var prefix = arguments.length > 3 && arguments[3] !== undefined ? arguments[3] : null;

				if (!document) {
					document = this.CreateDocument();
				}
				localName = localName || this.localName;
				namespaceUri = namespaceUri || this.NamespaceURI;
				prefix = prefix || this.prefix;
				var xn = document.createElementNS(this.NamespaceURI, (prefix ? prefix + ':' : "") + localName);
				document.importNode(xn, true);
				return xn;
			}
		}, {
			key: 'CreateDocument',
			value: function CreateDocument() {
				return XmlObject.CreateDocument(this.localName, this.NamespaceURI, this.Prefix);
			}
		}, {
			key: 'Element',
			get: function get() {
				return this.element;
			}
		}, {
			key: 'Prefix',
			get: function get() {
				return this.prefix;
			},
			set: function set(value) {
				this.prefix = value;
			}
		}, {
			key: 'LocalName',
			get: function get() {
				return this.localName;
			}
		}, {
			key: 'NamespaceURI',
			get: function get() {
				return this.namespaceURI || null;
			}
		}], [{
			key: 'LoadXml',
			value: function LoadXml(param) {
				var xml = new this();
				xml.LoadXml(param);
				return xml;
			}
		}, {
			key: 'GetElement',
			value: function GetElement(element, name) {
				var required = arguments.length > 2 && arguments[2] !== undefined ? arguments[2] : true;

				var xmlNodeList = element.getElementsByTagName(name);
				if (required && xmlNodeList.length === 0) {
					throw new XmlError(XE.ELEMENT_MISSING, name, element.localName);
				}
				return xmlNodeList[0] || null;
			}
		}, {
			key: 'GetAttribute',
			value: function GetAttribute(element, attrName, defaultValue) {
				var required = arguments.length > 3 && arguments[3] !== undefined ? arguments[3] : true;

				if (element.hasAttribute(attrName)) {
					return element.getAttribute(attrName);
				} else {
					if (required) {
						throw new XmlError(XE.ATTRIBUTE_MISSING, attrName, element.localName);
					}
					return defaultValue;
				}
			}
		}, {
			key: 'GetElementById',
			value: function GetElementById(node, idValue) {
				if (node == null || idValue == null) {
					return null;
				}
				var xel = null;
				if (node.nodeType === XmlNodeType.Document) {
					xel = node.getElementById(idValue);
				}
				if (xel == null) {
					xel = SelectSingleNode(node, '//*[@Id=\'' + idValue + '\']');
					if (xel == null) {
						xel = SelectSingleNode(node, '//*[@ID=\'' + idValue + '\']');
						if (xel == null) {
							xel = SelectSingleNode(node, '//*[@id=\'' + idValue + '\']');
						}
					}
				}
				return xel;
			}
		}, {
			key: 'CreateDocument',
			value: function CreateDocument() {
				var root = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : DEFAULT_ROOT_NAME;
				var namespaceUri = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : null;
				var prefix = arguments.length > 2 && arguments[2] !== undefined ? arguments[2] : null;

				var namePrefix = "";
				var nsPrefix = "";
				var namespaceUri2 = "";
				if (prefix) {
					namePrefix = prefix + ":";
					nsPrefix = ":" + prefix;
				}
				if (namespaceUri) {
					namespaceUri2 = ' xmlns' + nsPrefix + '="' + namespaceUri + '"';
				}
				var name = '' + namePrefix + root;
				var doc = new DOMParser().parseFromString('<' + name + namespaceUri2 + '></' + name + '>', APPLICATION_XML);
				return doc;
			}
		}, {
			key: 'GetChildren',
			value: function GetChildren(node, localName, nameSpace) {
				node = node.documentElement || node;
				var res = [];
				for (var i = 0; i < node.childNodes.length; i++) {
					var child = node.childNodes[i];
					if (child.nodeType === XmlNodeType.Element && child.localName === localName && (child.namespaceURI === nameSpace || !nameSpace)) {
						res.push(child);
					}
				}
				return res;
			}
		}, {
			key: 'GetFirstChild',
			value: function GetFirstChild(node, localName, nameSpace) {
				node = node.documentElement || node;
				for (var i = 0; i < node.childNodes.length; i++) {
					var child = node.childNodes[i];
					if (child.nodeType === XmlNodeType.Element && child.localName === localName && (child.namespaceURI === nameSpace || !nameSpace)) {
						return child;
					}
				}
				return null;
			}
		}, {
			key: 'GetChild',
			value: function GetChild(node, localName, nameSpace) {
				var required = arguments.length > 3 && arguments[3] !== undefined ? arguments[3] : true;

				for (var i = 0; i < node.childNodes.length; i++) {
					var child = node.childNodes[i];
					if (child.nodeType === XmlNodeType.Element && child.localName === localName && (child.namespaceURI === nameSpace || !nameSpace)) {
						return child;
					}
				}
				if (required) {
					throw new XmlError(XE.ELEMENT_MISSING, localName, node.localName);
				}
				return null;
			}
		}]);

		return XmlObject;
	}();

	var XmlCollection = function (_XmlObject) {
		_inherits(XmlCollection, _XmlObject);

		function XmlCollection() {
			_classCallCheck(this, XmlCollection);

			var _this2 = _possibleConstructorReturn(this, (XmlCollection.__proto__ || Object.getPrototypeOf(XmlCollection)).apply(this, arguments));

			_this2.items = new Array();
			return _this2;
		}

		_createClass(XmlCollection, [{
			key: 'HasChanged',
			value: function HasChanged() {
				var res = _get(XmlCollection.prototype.__proto__ || Object.getPrototypeOf(XmlCollection.prototype), 'HasChanged', this).call(this);
				var changed = this.Some(function (item) {
					return item.HasChanged();
				});
				return res || changed;
			}
		}, {
			key: 'Item',
			value: function Item(index) {
				return this.items[index] || null;
			}
		}, {
			key: 'Add',
			value: function Add(item) {
				this.items.push(item);
				this.element = null;
			}
		}, {
			key: 'Pop',
			value: function Pop() {
				this.element = null;
				return this.items.pop();
			}
		}, {
			key: 'RemoveAt',
			value: function RemoveAt(index) {
				this.items = this.items.filter(function (item, index2) {
					return index2 !== index;
				});
				this.element = null;
			}
		}, {
			key: 'Clear',
			value: function Clear() {
				this.items = new Array();
				this.element = null;
			}
		}, {
			key: 'GetIterator',
			value: function GetIterator() {
				return this.items;
			}
		}, {
			key: 'ForEach',
			value: function ForEach(cb) {
				this.GetIterator().forEach(cb);
			}
		}, {
			key: 'Map',
			value: function Map(cb) {
				return new Collection(this.GetIterator().map(cb));
			}
		}, {
			key: 'Filter',
			value: function Filter(cb) {
				return new Collection(this.GetIterator().filter(cb));
			}
		}, {
			key: 'Sort',
			value: function Sort(cb) {
				return new Collection(this.GetIterator().sort(cb));
			}
		}, {
			key: 'Every',
			value: function Every(cb) {
				return this.GetIterator().every(cb);
			}
		}, {
			key: 'Some',
			value: function Some(cb) {
				return this.GetIterator().some(cb);
			}
		}, {
			key: 'IsEmpty',
			value: function IsEmpty() {
				return this.Count === 0;
			}
		}, {
			key: 'OnGetXml',
			value: function OnGetXml(element) {
				var _iteratorNormalCompletion = true;
				var _didIteratorError = false;
				var _iteratorError = undefined;

				try {
					for (var _iterator = this.GetIterator()[Symbol.iterator](), _step; !(_iteratorNormalCompletion = (_step = _iterator.next()).done); _iteratorNormalCompletion = true) {
						var item = _step.value;

						var el = item.GetXml();
						if (el) {
							element.appendChild(el);
						}
					}
				} catch (err) {
					_didIteratorError = true;
					_iteratorError = err;
				} finally {
					try {
						if (!_iteratorNormalCompletion && _iterator.return) {
							_iterator.return();
						}
					} finally {
						if (_didIteratorError) {
							throw _iteratorError;
						}
					}
				}
			}
		}, {
			key: 'OnLoadXml',
			value: function OnLoadXml(element) {
				var self = this.GetStatic();
				if (!self.parser) {
					throw new XmlError(XE.XML_EXCEPTION, self.localName + ' doesn\'t have required \'parser\' in @XmlElement');
				}
				for (var i = 0; i < element.childNodes.length; i++) {
					var node = element.childNodes.item(i);
					if (!(node.nodeType === XmlNodeType.Element && node.localName === self.parser.localName && node.namespaceURI == self.namespaceURI)) {
						continue;
					}
					var el = node;
					var item = new self.parser();
					item.LoadXml(el);
					this.Add(item);
				}
			}
		}, {
			key: 'Count',
			get: function get() {
				return this.items.length;
			}
		}]);

		return XmlCollection;
	}(XmlObject);

	var NamespaceManager = function (_Collection) {
		_inherits(NamespaceManager, _Collection);

		function NamespaceManager() {
			_classCallCheck(this, NamespaceManager);

			return _possibleConstructorReturn(this, (NamespaceManager.__proto__ || Object.getPrototypeOf(NamespaceManager)).apply(this, arguments));
		}

		_createClass(NamespaceManager, [{
			key: 'Add',
			value: function Add(item) {
				item.prefix = item.prefix || "";
				item.namespace = item.namespace || "";
				_get(NamespaceManager.prototype.__proto__ || Object.getPrototypeOf(NamespaceManager.prototype), 'Add', this).call(this, item);
			}
		}, {
			key: 'GetPrefix',
			value: function GetPrefix(prefix) {
				var start = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : this.Count - 1;

				var lim = this.Count - 1;
				prefix = prefix || "";
				if (start > lim) {
					start = lim;
				}
				for (var i = start; i >= 0; i--) {
					var item = this.items[i];
					if (item.prefix === prefix) {
						return item;
					}
				}
				return null;
			}
		}, {
			key: 'GetNamespace',
			value: function GetNamespace(namespaceUrl) {
				var start = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : this.Count - 1;

				var lim = this.Count - 1;
				namespaceUrl = namespaceUrl || "";
				if (start > lim) {
					start = lim;
				}
				for (var i = start; i >= 0; i--) {
					var item = this.items[i];
					if (item.namespace === namespaceUrl) {
						return item;
					}
				}
				return null;
			}
		}]);

		return NamespaceManager;
	}(Collection);

	var XmlXades = {
		DefaultPrefix: "xades",

		NamespaceURI: "http://uri.etsi.org/01903/v1.3.2#",

		SignedPropertiesType: "http://uri.etsi.org/01903/v1.3.2#SignedProperties",
		ElementNames: {
			Any: "Any",
			ByName: "ByName",
			ByKey: "ByKey",
			AttrAuthoritiesCertValues: "AttrAuthoritiesCertValues",
			AttributeRevocationValues: "AttributeRevocationValues",
			AttributeCertificateRefs: "AttributeCertificateRefs",
			AttributeRevocationRefs: "AttributeRevocationRefs",
			QualifyingProperties: "QualifyingProperties",
			QualifyingPropertiesReference: "QualifyingPropertiesReference",
			SignedProperties: "SignedProperties",
			SignedSignatureProperties: "SignedSignatureProperties",
			SignedDataObjectProperties: "SignedDataObjectProperties",
			UnsignedProperties: "UnsignedProperties",
			UnsignedSignatureProperties: "UnsignedSignatureProperties",
			UnsignedDataObjectProperties: "UnsignedDataObjectProperties",
			UnsignedDataObjectProperty: "UnsignedDataObjectProperty",
			SigningTime: "SigningTime",
			SigningCertificate: "SigningCertificate",
			SignaturePolicyIdentifier: "SignaturePolicyIdentifier",
			SignatureProductionPlace: "SignatureProductionPlace",
			SignerRole: "SignerRole",
			Cert: "Cert",
			CertDigest: "CertDigest",
			IssuerSerial: "IssuerSerial",
			DataObjectFormat: "DataObjectFormat",
			CommitmentTypeIndication: "CommitmentTypeIndication",
			AllDataObjectsTimeStamp: "AllDataObjectsTimeStamp",
			IndividualDataObjectsTimeStamp: "IndividualDataObjectsTimeStamp",
			HashDataInfo: "HashDataInfo",
			EncapsulatedTimeStamp: "EncapsulatedTimeStamp",
			XMLTimeStamp: "XMLTimeStamp",
			XAdESTimeStamp: "XAdESTimeStamp",
			OtherTimeStamp: "OtherTimeStamp",
			Description: "Description",
			ObjectIdentifier: "ObjectIdentifier",
			MimeType: "MimeType",
			Encoding: "Encoding",
			Identifier: "Identifier",
			DocumentationReferences: "DocumentationReferences",
			DocumentationReference: "DocumentationReference",
			CommitmentTypeId: "CommitmentTypeId",
			ObjectReference: "ObjectReference",
			CommitmentTypeQualifiers: "CommitmentTypeQualifiers",
			AllSignedDataObjects: "AllSignedDataObjects",
			CommitmentTypeQualifier: "CommitmentTypeQualifier",
			SignaturePolicyId: "SignaturePolicyId",
			SignaturePolicyImplied: "SignaturePolicyImplied",
			SigPolicyId: "SigPolicyId",
			SigPolicyHash: "SigPolicyHash",
			SigPolicyQualifier: "SigPolicyQualifier",
			SigPolicyQualifiers: "SigPolicyQualifiers",
			SPURI: "SPURI",
			SPUserNotice: "SPUserNotice",
			NoticeRef: "NoticeRef",
			ExplicitText: "ExplicitText",
			ClaimedRoles: "ClaimedRoles",
			ClaimedRole: "ClaimedRole",
			CertifiedRoles: "CertifiedRoles",
			CertifiedRole: "CertifiedRole",
			Organization: "Organization",
			NoticeNumbers: "NoticeNumbers",
			Int: "int",
			City: "City",
			PostalCode: "PostalCode",
			StateOrProvince: "StateOrProvince",
			CountryName: "CountryName",
			CounterSignature: "CounterSignature",
			SignatureTimeStamp: "SignatureTimeStamp",
			CompleteCertificateRefs: "CompleteCertificateRefs",
			CompleteRevocationRefs: "CompleteRevocationRefs",
			SigAndRefsTimeStamp: "SigAndRefsTimeStamp",
			RefsOnlyTimeStamp: "RefsOnlyTimeStamp",
			CertificateValues: "CertificateValues",
			RevocationValues: "RevocationValues",
			ArchiveTimeStamp: "ArchiveTimeStamp",
			CertRefs: "CertRefs",
			CRLRefs: "CRLRefs",
			CRLRef: "CRLRef",
			OCSPRefs: "OCSPRefs",
			OtherRefs: "OtherRefs",
			OtherRef: "OtherRef",
			DigestAlgAndValue: "DigestAlgAndValue",
			CRLIdentifier: "CRLIdentifier",
			Issuer: "Issuer",
			IssueTime: "IssueTime",
			Number: "Number",
			OCSPRef: "OCSPRef",
			OCSPIdentifier: "OCSPIdentifier",
			ResponderID: "ResponderID",
			ProducedAt: "ProducedAt",
			EncapsulatedX509Certificate: "EncapsulatedX509Certificate",
			OtherCertificate: "OtherCertificate",
			CRLValues: "CRLValues",
			OCSPValues: "OCSPValues",
			OtherValues: "OtherValues",
			OtherValue: "OtherValue",
			EncapsulatedCRLValue: "EncapsulatedCRLValue",
			EncapsulatedOCSPValue: "EncapsulatedOCSPValue",
			ReferenceInfo: "ReferenceInfo",
			Include: "Include"
		},
		AttributeNames: {
			Id: "Id",
			Encoding: "Encoding",
			Target: "Target",
			ObjectReference: "ObjectReference",
			Qualifier: "Qualifier",
			Uri: "uri",
			URI: "URI",
			ReferencedData: "referencedData"
		}
	};

	var XadesObject = function (_super) {
		__extends(XadesObject, _super);
		function XadesObject() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		XadesObject = __decorate([XmlElement({
			localName: "xades",
			namespaceURI: XmlXades.NamespaceURI,
			prefix: XmlXades.DefaultPrefix
		})], XadesObject);
		return XadesObject;
	}(XmlObject);
	var XadesCollection = function (_super) {
		__extends(XadesCollection, _super);
		function XadesCollection() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		XadesCollection = __decorate([XmlElement({
			localName: "xades_collection",
			namespaceURI: XmlXades.NamespaceURI,
			prefix: XmlXades.DefaultPrefix
		})], XadesCollection);
		return XadesCollection;
	}(XmlCollection);

	var Any = function (_super) {
		__extends(Any, _super);
		function Any() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		__decorate([XmlContent()], Any.prototype, "Value", void 0);
		Any = __decorate([XmlElement({
			localName: XmlXades.ElementNames.Any
		})], Any);
		return Any;
	}(XadesObject);
	var AnyCollection = function (_super) {
		__extends(AnyCollection, _super);
		function AnyCollection() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		AnyCollection = __decorate([XmlElement({
			localName: XmlXades.ElementNames.Any
		})], AnyCollection);
		return AnyCollection;
	}(XadesCollection);

	var XmlEncodingConverter = {
		get: function get(value) {
			switch (value) {
				case "der":
				case "ber":
				case "cer":
				case "per":
				case "xer":
					return "http://uri.etsi.org/01903/v1.2.2#" + value.toUpperCase();
			}
			return void 0;
		},
		set: function set(value) {
			var regexp = /#(\w+)$/;
			var res = regexp.exec(value);
			if (res) {
				return res[1].toLowerCase();
			}
			return null;
		}
	};
	var EncapsulatedPKIData = function (_super) {
		__extends(EncapsulatedPKIData, _super);
		function EncapsulatedPKIData() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		__decorate([XmlAttribute({
			localName: XmlXades.AttributeNames.Id,
			defaultValue: ""
		})], EncapsulatedPKIData.prototype, "Id", void 0);
		__decorate([XmlAttribute({
			localName: XmlXades.AttributeNames.Encoding,
			defaultValue: null,
			converter: XmlEncodingConverter
		})], EncapsulatedPKIData.prototype, "Encoding", void 0);
		__decorate([XmlContent({
			required: true,
			converter: XmlBase64Converter
		})], EncapsulatedPKIData.prototype, "Value", void 0);
		EncapsulatedPKIData = __decorate([XmlElement({
			localName: "EncapsulatedPKIData"
		})], EncapsulatedPKIData);
		return EncapsulatedPKIData;
	}(XadesObject);

	var OtherCertificate = function (_super) {
		__extends(OtherCertificate, _super);
		function OtherCertificate() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		OtherCertificate = __decorate([XmlElement({ localName: XmlXades.ElementNames.OtherCertificate })], OtherCertificate);
		return OtherCertificate;
	}(Any);
	var OtherCertificateCollection = function (_super) {
		__extends(OtherCertificateCollection, _super);
		function OtherCertificateCollection() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		OtherCertificateCollection = __decorate([XmlElement({ localName: "OtherCertificateCollection", parser: OtherCertificate })], OtherCertificateCollection);
		return OtherCertificateCollection;
	}(XadesCollection);
	var EncapsulatedX509Certificate = function (_super) {
		__extends(EncapsulatedX509Certificate, _super);
		function EncapsulatedX509Certificate() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		EncapsulatedX509Certificate = __decorate([XmlElement({ localName: XmlXades.ElementNames.EncapsulatedX509Certificate })], EncapsulatedX509Certificate);
		return EncapsulatedX509Certificate;
	}(EncapsulatedPKIData);
	var EncapsulatedX509CertificateCollection = function (_super) {
		__extends(EncapsulatedX509CertificateCollection, _super);
		function EncapsulatedX509CertificateCollection() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		EncapsulatedX509CertificateCollection = __decorate([XmlElement({ localName: "EncapsulatedX509CertificateCollection", parser: EncapsulatedX509Certificate })], EncapsulatedX509CertificateCollection);
		return EncapsulatedX509CertificateCollection;
	}(XadesCollection);
	var CertificateValues = function (_super) {
		__extends(CertificateValues, _super);
		function CertificateValues() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		__decorate([XmlAttribute({ localName: XmlXades.AttributeNames.Id, defaultValue: "" })], CertificateValues.prototype, "Id", void 0);
		__decorate([XmlChildElement({ parser: EncapsulatedX509CertificateCollection, noRoot: true })], CertificateValues.prototype, "EncapsulatedX509Certificates", void 0);
		__decorate([XmlChildElement({ parser: OtherCertificateCollection, noRoot: true })], CertificateValues.prototype, "OtherCertificates", void 0);
		CertificateValues = __decorate([XmlElement({ localName: XmlXades.ElementNames.CertificateValues })], CertificateValues);
		return CertificateValues;
	}(XadesObject);

	var Identifier = function (_super) {
		__extends(Identifier, _super);
		function Identifier() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		__decorate([XmlAttribute({
			localName: XmlXades.AttributeNames.Qualifier
		})], Identifier.prototype, "Qualifier", void 0);
		__decorate([XmlContent({
			defaultValue: "",
			required: true
		})], Identifier.prototype, "Value", void 0);
		Identifier = __decorate([XmlElement({
			localName: XmlXades.ElementNames.Identifier
		})], Identifier);
		return Identifier;
	}(XadesObject);
	var DocumentationReference = function (_super) {
		__extends(DocumentationReference, _super);
		function DocumentationReference() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		DocumentationReference.prototype.OnLoadXml = function (e) {
			if (e.textContent) {
				this.Uri = e.textContent;
			}
		};
		DocumentationReference.prototype.OnGetXml = function (e) {
			if (this.Uri) {
				e.textContent = this.Uri;
			}
		};
		__decorate([XmlContent({
			defaultValue: "",
			required: true
		})], DocumentationReference.prototype, "Uri", void 0);
		DocumentationReference = __decorate([XmlElement({
			localName: XmlXades.ElementNames.DocumentationReference
		})], DocumentationReference);
		return DocumentationReference;
	}(XadesObject);
	var DocumentationReferences = function (_super) {
		__extends(DocumentationReferences, _super);
		function DocumentationReferences() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		DocumentationReferences = __decorate([XmlElement({
			localName: XmlXades.ElementNames.DocumentationReferences,
			parser: DocumentationReference
		})], DocumentationReferences);
		return DocumentationReferences;
	}(XadesCollection);
	var ObjectIdentifier = function (_super) {
		__extends(ObjectIdentifier, _super);
		function ObjectIdentifier() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		__decorate([XmlChildElement({
			parser: Identifier,
			required: true
		})], ObjectIdentifier.prototype, "Identifier", void 0);
		__decorate([XmlChildElement({
			localName: XmlXades.ElementNames.Description,
			namespaceURI: XmlXades.NamespaceURI,
			prefix: XmlXades.DefaultPrefix,
			defaultValue: ""
		})], ObjectIdentifier.prototype, "Description", void 0);
		__decorate([XmlChildElement({
			parser: DocumentationReferences
		})], ObjectIdentifier.prototype, "DocumentationReferences", void 0);
		ObjectIdentifier = __decorate([XmlElement({
			localName: XmlXades.ElementNames.ObjectIdentifier
		})], ObjectIdentifier);
		return ObjectIdentifier;
	}(XadesObject);

	var CommitmentTypeQualifier = function (_super) {
		__extends(CommitmentTypeQualifier, _super);
		function CommitmentTypeQualifier() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		CommitmentTypeQualifier = __decorate([XmlElement({ localName: XmlXades.ElementNames.CommitmentTypeQualifier })], CommitmentTypeQualifier);
		return CommitmentTypeQualifier;
	}(Any);
	var CommitmentTypeQualifiers = function (_super) {
		__extends(CommitmentTypeQualifiers, _super);
		function CommitmentTypeQualifiers() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		CommitmentTypeQualifiers = __decorate([XmlElement({ localName: XmlXades.ElementNames.CommitmentTypeQualifiers, parser: CommitmentTypeQualifier })], CommitmentTypeQualifiers);
		return CommitmentTypeQualifiers;
	}(XadesCollection);
	var ObjectReference = function (_super) {
		__extends(ObjectReference, _super);
		function ObjectReference() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		__decorate([XmlContent({ required: true })], ObjectReference.prototype, "Value", void 0);
		ObjectReference = __decorate([XmlElement({ localName: XmlXades.ElementNames.ObjectReference })], ObjectReference);
		return ObjectReference;
	}(XadesObject);
	var ObjectReferenceCollection = function (_super) {
		__extends(ObjectReferenceCollection, _super);
		function ObjectReferenceCollection() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		ObjectReferenceCollection = __decorate([XmlElement({ localName: "ObjectReferences", parser: ObjectReference })], ObjectReferenceCollection);
		return ObjectReferenceCollection;
	}(XadesCollection);
	var XmlAllSignedDataObjectsConverter = {
		set: function set(value) {
			return true;
		},
		get: function get(value) {
			return void 0;
		}
	};
	var CommitmentTypeIndication = function (_super) {
		__extends(CommitmentTypeIndication, _super);
		function CommitmentTypeIndication() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		__decorate([XmlChildElement({
			localName: XmlXades.ElementNames.CommitmentTypeId,
			required: true,
			namespaceURI: XmlXades.NamespaceURI,
			prefix: XmlXades.DefaultPrefix,
			parser: ObjectIdentifier
		})], CommitmentTypeIndication.prototype, "CommitmentTypeId", void 0);
		__decorate([XmlChildElement({ parser: ObjectReferenceCollection, noRoot: true })], CommitmentTypeIndication.prototype, "ObjectReference", void 0);
		__decorate([XmlChildElement({
			localName: XmlXades.ElementNames.AllSignedDataObjects,
			namespaceURI: XmlXades.NamespaceURI,
			prefix: XmlXades.DefaultPrefix,
			converter: XmlAllSignedDataObjectsConverter,
			defaultValue: false
		})], CommitmentTypeIndication.prototype, "AllSignedDataObjects", void 0);
		__decorate([XmlChildElement({ localName: XmlXades.ElementNames.CommitmentTypeQualifiers, parser: CommitmentTypeQualifier })], CommitmentTypeIndication.prototype, "CommitmentTypeQualifiers", void 0);
		CommitmentTypeIndication = __decorate([XmlElement({ localName: XmlXades.ElementNames.CommitmentTypeIndication })], CommitmentTypeIndication);
		return CommitmentTypeIndication;
	}(XadesObject);

	function getParametersValue(parameters, name, defaultValue) {
		if (parameters instanceof Object === false) return defaultValue;

		if (name in parameters) return parameters[name];

		return defaultValue;
	}

	function bufferToHexCodes(inputBuffer) {
		var inputOffset = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : 0;
		var inputLength = arguments.length > 2 && arguments[2] !== undefined ? arguments[2] : inputBuffer.byteLength;

		var result = "";

		var _iteratorNormalCompletion2 = true;
		var _didIteratorError2 = false;
		var _iteratorError2 = undefined;

		try {
			for (var _iterator2 = new Uint8Array(inputBuffer, inputOffset, inputLength)[Symbol.iterator](), _step2; !(_iteratorNormalCompletion2 = (_step2 = _iterator2.next()).done); _iteratorNormalCompletion2 = true) {
				var item = _step2.value;

				var str = item.toString(16).toUpperCase();
				result = result + (str.length === 1 ? "0" : "") + str;
			}
		} catch (err) {
			_didIteratorError2 = true;
			_iteratorError2 = err;
		} finally {
			try {
				if (!_iteratorNormalCompletion2 && _iterator2.return) {
					_iterator2.return();
				}
			} finally {
				if (_didIteratorError2) {
					throw _iteratorError2;
				}
			}
		}

		return result;
	}

	function checkBufferParams(baseBlock, inputBuffer, inputOffset, inputLength) {
		if (inputBuffer instanceof ArrayBuffer === false) {
			baseBlock.error = "Wrong parameter: inputBuffer must be \"ArrayBuffer\"";
			return false;
		}

		if (inputBuffer.byteLength === 0) {
			baseBlock.error = "Wrong parameter: inputBuffer has zero length";
			return false;
		}

		if (inputOffset < 0) {
			baseBlock.error = "Wrong parameter: inputOffset less than zero";
			return false;
		}

		if (inputLength < 0) {
			baseBlock.error = "Wrong parameter: inputLength less than zero";
			return false;
		}

		if (inputBuffer.byteLength - inputOffset - inputLength < 0) {
			baseBlock.error = "End of input reached before message was fully decoded (inconsistent offset and length values)";
			return false;
		}

		return true;
	}

	function utilFromBase(inputBuffer, inputBase) {
		var result = 0;

		for (var i = inputBuffer.length - 1; i >= 0; i--) {
			result += inputBuffer[inputBuffer.length - 1 - i] * Math.pow(2, inputBase * i);
		}return result;
	}

	function utilToBase(value, base) {
		var reserved = arguments.length > 2 && arguments[2] !== undefined ? arguments[2] : 0;

		var internalReserved = reserved || -1;
		var internalValue = value;

		var result = 0;
		var biggest = Math.pow(2, base);

		for (var i = 1; i < 8; i++) {
			if (value < biggest) {
				var retBuf = void 0;

				if (internalReserved < 0) {
					retBuf = new ArrayBuffer(i);
					result = i;
				} else {
					if (internalReserved < i) return new ArrayBuffer(0);

					retBuf = new ArrayBuffer(internalReserved);

					result = internalReserved;
				}

				var retView = new Uint8Array(retBuf);

				for (var j = i - 1; j >= 0; j--) {
					var basis = Math.pow(2, j * base);

					retView[result - j - 1] = Math.floor(internalValue / basis);
					internalValue -= retView[result - j - 1] * basis;
				}

				return retBuf;
			}

			biggest *= Math.pow(2, base);
		}

		return new ArrayBuffer(0);
	}

	function utilConcatBuf() {
		var outputLength = 0;
		var prevLength = 0;

		for (var _len5 = arguments.length, buffers = Array(_len5), _key5 = 0; _key5 < _len5; _key5++) {
			buffers[_key5] = arguments[_key5];
		}

		var _iteratorNormalCompletion3 = true;
		var _didIteratorError3 = false;
		var _iteratorError3 = undefined;

		try {

			for (var _iterator3 = buffers[Symbol.iterator](), _step3; !(_iteratorNormalCompletion3 = (_step3 = _iterator3.next()).done); _iteratorNormalCompletion3 = true) {
				var buffer = _step3.value;

				outputLength += buffer.byteLength;
			}
		} catch (err) {
			_didIteratorError3 = true;
			_iteratorError3 = err;
		} finally {
			try {
				if (!_iteratorNormalCompletion3 && _iterator3.return) {
					_iterator3.return();
				}
			} finally {
				if (_didIteratorError3) {
					throw _iteratorError3;
				}
			}
		}

		var retBuf = new ArrayBuffer(outputLength);
		var retView = new Uint8Array(retBuf);

		var _iteratorNormalCompletion4 = true;
		var _didIteratorError4 = false;
		var _iteratorError4 = undefined;

		try {
			for (var _iterator4 = buffers[Symbol.iterator](), _step4; !(_iteratorNormalCompletion4 = (_step4 = _iterator4.next()).done); _iteratorNormalCompletion4 = true) {
				var _buffer = _step4.value;

				retView.set(new Uint8Array(_buffer), prevLength);
				prevLength += _buffer.byteLength;
			}
		} catch (err) {
			_didIteratorError4 = true;
			_iteratorError4 = err;
		} finally {
			try {
				if (!_iteratorNormalCompletion4 && _iterator4.return) {
					_iterator4.return();
				}
			} finally {
				if (_didIteratorError4) {
					throw _iteratorError4;
				}
			}
		}

		return retBuf;
	}

	function utilConcatView() {
		var outputLength = 0;
		var prevLength = 0;

		for (var _len6 = arguments.length, views = Array(_len6), _key6 = 0; _key6 < _len6; _key6++) {
			views[_key6] = arguments[_key6];
		}

		var _iteratorNormalCompletion5 = true;
		var _didIteratorError5 = false;
		var _iteratorError5 = undefined;

		try {

			for (var _iterator5 = views[Symbol.iterator](), _step5; !(_iteratorNormalCompletion5 = (_step5 = _iterator5.next()).done); _iteratorNormalCompletion5 = true) {
				var view = _step5.value;

				outputLength += view.length;
			}
		} catch (err) {
			_didIteratorError5 = true;
			_iteratorError5 = err;
		} finally {
			try {
				if (!_iteratorNormalCompletion5 && _iterator5.return) {
					_iterator5.return();
				}
			} finally {
				if (_didIteratorError5) {
					throw _iteratorError5;
				}
			}
		}

		var retBuf = new ArrayBuffer(outputLength);
		var retView = new Uint8Array(retBuf);

		var _iteratorNormalCompletion6 = true;
		var _didIteratorError6 = false;
		var _iteratorError6 = undefined;

		try {
			for (var _iterator6 = views[Symbol.iterator](), _step6; !(_iteratorNormalCompletion6 = (_step6 = _iterator6.next()).done); _iteratorNormalCompletion6 = true) {
				var _view2 = _step6.value;

				retView.set(_view2, prevLength);
				prevLength += _view2.length;
			}
		} catch (err) {
			_didIteratorError6 = true;
			_iteratorError6 = err;
		} finally {
			try {
				if (!_iteratorNormalCompletion6 && _iterator6.return) {
					_iterator6.return();
				}
			} finally {
				if (_didIteratorError6) {
					throw _iteratorError6;
				}
			}
		}

		return retView;
	}

	function utilDecodeTC() {
		var buf = new Uint8Array(this.valueHex);

		if (this.valueHex.byteLength >= 2) {
			var condition1 = buf[0] === 0xFF && buf[1] & 0x80;
			var condition2 = buf[0] === 0x00 && (buf[1] & 0x80) === 0x00;

			if (condition1 || condition2) this.warnings.push("Needlessly long format");
		}

		var bigIntBuffer = new ArrayBuffer(this.valueHex.byteLength);
		var bigIntView = new Uint8Array(bigIntBuffer);
		for (var i = 0; i < this.valueHex.byteLength; i++) {
			bigIntView[i] = 0;
		}bigIntView[0] = buf[0] & 0x80;

		var bigInt = utilFromBase(bigIntView, 8);

		var smallIntBuffer = new ArrayBuffer(this.valueHex.byteLength);
		var smallIntView = new Uint8Array(smallIntBuffer);
		for (var j = 0; j < this.valueHex.byteLength; j++) {
			smallIntView[j] = buf[j];
		}smallIntView[0] &= 0x7F;

		var smallInt = utilFromBase(smallIntView, 8);


		return smallInt - bigInt;
	}

	function utilEncodeTC(value) {
		var modValue = value < 0 ? value * -1 : value;
		var bigInt = 128;

		for (var i = 1; i < 8; i++) {
			if (modValue <= bigInt) {
				if (value < 0) {
					var smallInt = bigInt - modValue;

					var _retBuf = utilToBase(smallInt, 8, i);
					var _retView = new Uint8Array(_retBuf);

					_retView[0] |= 0x80;

					return _retBuf;
				}

				var retBuf = utilToBase(modValue, 8, i);
				var retView = new Uint8Array(retBuf);

				if (retView[0] & 0x80) {
					var tempBuf = retBuf.slice(0);
					var tempView = new Uint8Array(tempBuf);

					retBuf = new ArrayBuffer(retBuf.byteLength + 1);
					retView = new Uint8Array(retBuf);

					for (var k = 0; k < tempBuf.byteLength; k++) {
						retView[k + 1] = tempView[k];
					}retView[0] = 0x00;
				}

				return retBuf;
			}

			bigInt *= Math.pow(2, 8);
		}

		return new ArrayBuffer(0);
	}

	function isEqualBuffer(inputBuffer1, inputBuffer2) {
		if (inputBuffer1.byteLength !== inputBuffer2.byteLength) return false;

		var view1 = new Uint8Array(inputBuffer1);
		var view2 = new Uint8Array(inputBuffer2);

		for (var i = 0; i < view1.length; i++) {
			if (view1[i] !== view2[i]) return false;
		}

		return true;
	}

	function padNumber(inputNumber, fullLength) {
		var str = inputNumber.toString(10);
		var dif = fullLength - str.length;

		var padding = new Array(dif);
		for (var i = 0; i < dif; i++) {
			padding[i] = "0";
		}var paddingString = padding.join("");

		return paddingString.concat(str);
	}

	var powers2 = [new Uint8Array([1])];
	var digitsString = "0123456789";

	var LocalBaseBlock = function () {
		function LocalBaseBlock() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, LocalBaseBlock);

			this.blockLength = getParametersValue(parameters, "blockLength", 0);

			this.error = getParametersValue(parameters, "error", "");

			this.warnings = getParametersValue(parameters, "warnings", []);

			if ("valueBeforeDecode" in parameters) this.valueBeforeDecode = parameters.valueBeforeDecode.slice(0);else this.valueBeforeDecode = new ArrayBuffer(0);
		}

		_createClass(LocalBaseBlock, [{
			key: 'toJSON',
			value: function toJSON() {
				return {
					blockName: this.constructor.blockName(),
					blockLength: this.blockLength,
					error: this.error,
					warnings: this.warnings,
					valueBeforeDecode: bufferToHexCodes(this.valueBeforeDecode, 0, this.valueBeforeDecode.byteLength)
				};
			}
		}], [{
			key: 'blockName',
			value: function blockName() {
				return "baseBlock";
			}
		}]);

		return LocalBaseBlock;
	}();

	var LocalHexBlock = function LocalHexBlock(BaseClass) {
		return function (_BaseClass) {
			_inherits(LocalHexBlockMixin, _BaseClass);

			function LocalHexBlockMixin() {
				var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

				_classCallCheck(this, LocalHexBlockMixin);

				var _this4 = _possibleConstructorReturn(this, (LocalHexBlockMixin.__proto__ || Object.getPrototypeOf(LocalHexBlockMixin)).call(this, parameters));

				_this4.isHexOnly = getParametersValue(parameters, "isHexOnly", false);

				if ("valueHex" in parameters) _this4.valueHex = parameters.valueHex.slice(0);else _this4.valueHex = new ArrayBuffer(0);
				return _this4;
			}

			_createClass(LocalHexBlockMixin, [{
				key: 'fromBER',
				value: function fromBER(inputBuffer, inputOffset, inputLength) {
					if (checkBufferParams(this, inputBuffer, inputOffset, inputLength) === false) return -1;

					var intBuffer = new Uint8Array(inputBuffer, inputOffset, inputLength);

					if (intBuffer.length === 0) {
						this.warnings.push("Zero buffer length");
						return inputOffset;
					}

					this.valueHex = inputBuffer.slice(inputOffset, inputOffset + inputLength);


					this.blockLength = inputLength;

					return inputOffset + inputLength;
				}
			}, {
				key: 'toBER',
				value: function toBER() {
					var sizeOnly = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : false;

					if (this.isHexOnly !== true) {
						this.error = "Flag \"isHexOnly\" is not set, abort";
						return new ArrayBuffer(0);
					}

					if (sizeOnly === true) return new ArrayBuffer(this.valueHex.byteLength);

					return this.valueHex.slice(0);
				}
			}, {
				key: 'toJSON',
				value: function toJSON() {
					var object = {};

					try {
						object = _get(LocalHexBlockMixin.prototype.__proto__ || Object.getPrototypeOf(LocalHexBlockMixin.prototype), 'toJSON', this).call(this);
					} catch (ex) {}


					object.blockName = this.constructor.blockName();
					object.isHexOnly = this.isHexOnly;
					object.valueHex = bufferToHexCodes(this.valueHex, 0, this.valueHex.byteLength);

					return object;
				}
			}], [{
				key: 'blockName',
				value: function blockName() {
					return "hexBlock";
				}
			}]);

			return LocalHexBlockMixin;
		}(BaseClass);
	};

	var LocalIdentificationBlock = function (_LocalHexBlock) {
		_inherits(LocalIdentificationBlock, _LocalHexBlock);

		function LocalIdentificationBlock() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, LocalIdentificationBlock);

			var _this5 = _possibleConstructorReturn(this, (LocalIdentificationBlock.__proto__ || Object.getPrototypeOf(LocalIdentificationBlock)).call(this));

			if ("idBlock" in parameters) {
				_this5.isHexOnly = getParametersValue(parameters.idBlock, "isHexOnly", false);
				_this5.valueHex = getParametersValue(parameters.idBlock, "valueHex", new ArrayBuffer(0));


				_this5.tagClass = getParametersValue(parameters.idBlock, "tagClass", -1);
				_this5.tagNumber = getParametersValue(parameters.idBlock, "tagNumber", -1);
				_this5.isConstructed = getParametersValue(parameters.idBlock, "isConstructed", false);
			} else {
				_this5.tagClass = -1;
				_this5.tagNumber = -1;
				_this5.isConstructed = false;
			}
			return _this5;
		}

		_createClass(LocalIdentificationBlock, [{
			key: 'toBER',
			value: function toBER() {
				var sizeOnly = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : false;

				var firstOctet = 0;
				var retBuf = void 0;
				var retView = void 0;


				switch (this.tagClass) {
					case 1:
						firstOctet |= 0x00;
						break;
					case 2:
						firstOctet |= 0x40;
						break;
					case 3:
						firstOctet |= 0x80;
						break;
					case 4:
						firstOctet |= 0xC0;
						break;
					default:
						this.error = "Unknown tag class";
						return new ArrayBuffer(0);
				}

				if (this.isConstructed) firstOctet |= 0x20;

				if (this.tagNumber < 31 && !this.isHexOnly) {
					retBuf = new ArrayBuffer(1);
					retView = new Uint8Array(retBuf);

					if (!sizeOnly) {
						var number = this.tagNumber;
						number &= 0x1F;
						firstOctet |= number;

						retView[0] = firstOctet;
					}

					return retBuf;
				}

				if (this.isHexOnly === false) {
					var encodedBuf = utilToBase(this.tagNumber, 7);
					var encodedView = new Uint8Array(encodedBuf);
					var size = encodedBuf.byteLength;

					retBuf = new ArrayBuffer(size + 1);
					retView = new Uint8Array(retBuf);
					retView[0] = firstOctet | 0x1F;

					if (!sizeOnly) {
						for (var i = 0; i < size - 1; i++) {
							retView[i + 1] = encodedView[i] | 0x80;
						}retView[size] = encodedView[size - 1];
					}

					return retBuf;
				}

				retBuf = new ArrayBuffer(this.valueHex.byteLength + 1);
				retView = new Uint8Array(retBuf);

				retView[0] = firstOctet | 0x1F;

				if (sizeOnly === false) {
					var curView = new Uint8Array(this.valueHex);

					for (var _i = 0; _i < curView.length - 1; _i++) {
						retView[_i + 1] = curView[_i] | 0x80;
					}retView[this.valueHex.byteLength] = curView[curView.length - 1];
				}

				return retBuf;
			}
		}, {
			key: 'fromBER',
			value: function fromBER(inputBuffer, inputOffset, inputLength) {
				if (checkBufferParams(this, inputBuffer, inputOffset, inputLength) === false) return -1;

				var intBuffer = new Uint8Array(inputBuffer, inputOffset, inputLength);

				if (intBuffer.length === 0) {
					this.error = "Zero buffer length";
					return -1;
				}

				var tagClassMask = intBuffer[0] & 0xC0;

				switch (tagClassMask) {
					case 0x00:
						this.tagClass = 1;
						break;
					case 0x40:
						this.tagClass = 2;
						break;
					case 0x80:
						this.tagClass = 3;
						break;
					case 0xC0:
						this.tagClass = 4;
						break;
					default:
						this.error = "Unknown tag class";
						return -1;
				}

				this.isConstructed = (intBuffer[0] & 0x20) === 0x20;

				this.isHexOnly = false;

				var tagNumberMask = intBuffer[0] & 0x1F;

				if (tagNumberMask !== 0x1F) {
					this.tagNumber = tagNumberMask;
					this.blockLength = 1;
				} else {
						var count = 1;

						this.valueHex = new ArrayBuffer(255);
						var tagNumberBufferMaxLength = 255;
						var intTagNumberBuffer = new Uint8Array(this.valueHex);

						while (intBuffer[count] & 0x80) {
							intTagNumberBuffer[count - 1] = intBuffer[count] & 0x7F;
							count++;

							if (count >= intBuffer.length) {
								this.error = "End of input reached before message was fully decoded";
								return -1;
							}

							if (count === tagNumberBufferMaxLength) {
								tagNumberBufferMaxLength += 255;

								var _tempBuffer = new ArrayBuffer(tagNumberBufferMaxLength);
								var _tempBufferView = new Uint8Array(_tempBuffer);

								for (var i = 0; i < intTagNumberBuffer.length; i++) {
									_tempBufferView[i] = intTagNumberBuffer[i];
								}this.valueHex = new ArrayBuffer(tagNumberBufferMaxLength);
								intTagNumberBuffer = new Uint8Array(this.valueHex);
							}
						}

						this.blockLength = count + 1;
						intTagNumberBuffer[count - 1] = intBuffer[count] & 0x7F;
						var tempBuffer = new ArrayBuffer(count);
						var tempBufferView = new Uint8Array(tempBuffer);

						for (var _i2 = 0; _i2 < count; _i2++) {
							tempBufferView[_i2] = intTagNumberBuffer[_i2];
						}this.valueHex = new ArrayBuffer(count);
						intTagNumberBuffer = new Uint8Array(this.valueHex);
						intTagNumberBuffer.set(tempBufferView);

						if (this.blockLength <= 9) this.tagNumber = utilFromBase(intTagNumberBuffer, 7);else {
							this.isHexOnly = true;
							this.warnings.push("Tag too long, represented as hex-coded");
						}
					}

				if (this.tagClass === 1 && this.isConstructed) {
					switch (this.tagNumber) {
						case 1:
						case 2:
						case 5:
						case 6:
						case 9:
						case 14:
						case 23:
						case 24:
						case 31:
						case 32:
						case 33:
						case 34:
							this.error = "Constructed encoding used for primitive type";
							return -1;
						default:
					}
				}


				return inputOffset + this.blockLength;
			}
		}, {
			key: 'toJSON',
			value: function toJSON() {
				var object = {};

				try {
					object = _get(LocalIdentificationBlock.prototype.__proto__ || Object.getPrototypeOf(LocalIdentificationBlock.prototype), 'toJSON', this).call(this);
				} catch (ex) {}


				object.blockName = this.constructor.blockName();
				object.tagClass = this.tagClass;
				object.tagNumber = this.tagNumber;
				object.isConstructed = this.isConstructed;

				return object;
			}
		}], [{
			key: 'blockName',
			value: function blockName() {
				return "identificationBlock";
			}
		}]);

		return LocalIdentificationBlock;
	}(LocalHexBlock(LocalBaseBlock));

	var LocalLengthBlock = function (_LocalBaseBlock) {
		_inherits(LocalLengthBlock, _LocalBaseBlock);

		function LocalLengthBlock() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, LocalLengthBlock);

			var _this6 = _possibleConstructorReturn(this, (LocalLengthBlock.__proto__ || Object.getPrototypeOf(LocalLengthBlock)).call(this));

			if ("lenBlock" in parameters) {
				_this6.isIndefiniteForm = getParametersValue(parameters.lenBlock, "isIndefiniteForm", false);
				_this6.longFormUsed = getParametersValue(parameters.lenBlock, "longFormUsed", false);
				_this6.length = getParametersValue(parameters.lenBlock, "length", 0);
			} else {
				_this6.isIndefiniteForm = false;
				_this6.longFormUsed = false;
				_this6.length = 0;
			}
			return _this6;
		}

		_createClass(LocalLengthBlock, [{
			key: 'fromBER',
			value: function fromBER(inputBuffer, inputOffset, inputLength) {
				if (checkBufferParams(this, inputBuffer, inputOffset, inputLength) === false) return -1;

				var intBuffer = new Uint8Array(inputBuffer, inputOffset, inputLength);

				if (intBuffer.length === 0) {
					this.error = "Zero buffer length";
					return -1;
				}

				if (intBuffer[0] === 0xFF) {
					this.error = "Length block 0xFF is reserved by standard";
					return -1;
				}

				this.isIndefiniteForm = intBuffer[0] === 0x80;

				if (this.isIndefiniteForm === true) {
					this.blockLength = 1;
					return inputOffset + this.blockLength;
				}

				this.longFormUsed = !!(intBuffer[0] & 0x80);

				if (this.longFormUsed === false) {
					this.length = intBuffer[0];
					this.blockLength = 1;
					return inputOffset + this.blockLength;
				}

				var count = intBuffer[0] & 0x7F;

				if (count > 8) {
						this.error = "Too big integer";
						return -1;
					}

				if (count + 1 > intBuffer.length) {
					this.error = "End of input reached before message was fully decoded";
					return -1;
				}

				var lengthBufferView = new Uint8Array(count);

				for (var i = 0; i < count; i++) {
					lengthBufferView[i] = intBuffer[i + 1];
				}if (lengthBufferView[count - 1] === 0x00) this.warnings.push("Needlessly long encoded length");

				this.length = utilFromBase(lengthBufferView, 8);

				if (this.longFormUsed && this.length <= 127) this.warnings.push("Unneccesary usage of long length form");

				this.blockLength = count + 1;


				return inputOffset + this.blockLength;
			}
		}, {
			key: 'toBER',
			value: function toBER() {
				var sizeOnly = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : false;

				var retBuf = void 0;
				var retView = void 0;


				if (this.length > 127) this.longFormUsed = true;

				if (this.isIndefiniteForm) {
					retBuf = new ArrayBuffer(1);

					if (sizeOnly === false) {
						retView = new Uint8Array(retBuf);
						retView[0] = 0x80;
					}

					return retBuf;
				}

				if (this.longFormUsed === true) {
					var encodedBuf = utilToBase(this.length, 8);

					if (encodedBuf.byteLength > 127) {
						this.error = "Too big length";
						return new ArrayBuffer(0);
					}

					retBuf = new ArrayBuffer(encodedBuf.byteLength + 1);

					if (sizeOnly === true) return retBuf;

					var encodedView = new Uint8Array(encodedBuf);
					retView = new Uint8Array(retBuf);

					retView[0] = encodedBuf.byteLength | 0x80;

					for (var i = 0; i < encodedBuf.byteLength; i++) {
						retView[i + 1] = encodedView[i];
					}return retBuf;
				}

				retBuf = new ArrayBuffer(1);

				if (sizeOnly === false) {
					retView = new Uint8Array(retBuf);

					retView[0] = this.length;
				}

				return retBuf;
			}
		}, {
			key: 'toJSON',
			value: function toJSON() {
				var object = {};

				try {
					object = _get(LocalLengthBlock.prototype.__proto__ || Object.getPrototypeOf(LocalLengthBlock.prototype), 'toJSON', this).call(this);
				} catch (ex) {}


				object.blockName = this.constructor.blockName();
				object.isIndefiniteForm = this.isIndefiniteForm;
				object.longFormUsed = this.longFormUsed;
				object.length = this.length;

				return object;
			}
		}], [{
			key: 'blockName',
			value: function blockName() {
				return "lengthBlock";
			}
		}]);

		return LocalLengthBlock;
	}(LocalBaseBlock);

	var LocalValueBlock = function (_LocalBaseBlock2) {
		_inherits(LocalValueBlock, _LocalBaseBlock2);

		function LocalValueBlock() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, LocalValueBlock);

			return _possibleConstructorReturn(this, (LocalValueBlock.__proto__ || Object.getPrototypeOf(LocalValueBlock)).call(this, parameters));
		}

		_createClass(LocalValueBlock, [{
			key: 'fromBER',
			value: function fromBER(inputBuffer, inputOffset, inputLength) {
				throw TypeError("User need to make a specific function in a class which extends \"LocalValueBlock\"");
			}
		}, {
			key: 'toBER',
			value: function toBER() {
				var sizeOnly = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : false;

				throw TypeError("User need to make a specific function in a class which extends \"LocalValueBlock\"");
			}
		}], [{
			key: 'blockName',
			value: function blockName() {
				return "valueBlock";
			}
		}]);

		return LocalValueBlock;
	}(LocalBaseBlock);

	var BaseBlock = function (_LocalBaseBlock3) {
		_inherits(BaseBlock, _LocalBaseBlock3);

		function BaseBlock() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};
			var valueBlockType = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : LocalValueBlock;

			_classCallCheck(this, BaseBlock);

			var _this8 = _possibleConstructorReturn(this, (BaseBlock.__proto__ || Object.getPrototypeOf(BaseBlock)).call(this, parameters));

			if ("name" in parameters) _this8.name = parameters.name;
			if ("optional" in parameters) _this8.optional = parameters.optional;
			if ("primitiveSchema" in parameters) _this8.primitiveSchema = parameters.primitiveSchema;

			_this8.idBlock = new LocalIdentificationBlock(parameters);
			_this8.lenBlock = new LocalLengthBlock(parameters);
			_this8.valueBlock = new valueBlockType(parameters);
			return _this8;
		}

		_createClass(BaseBlock, [{
			key: 'fromBER',
			value: function fromBER(inputBuffer, inputOffset, inputLength) {
				var resultOffset = this.valueBlock.fromBER(inputBuffer, inputOffset, this.lenBlock.isIndefiniteForm === true ? inputLength : this.lenBlock.length);
				if (resultOffset === -1) {
					this.error = this.valueBlock.error;
					return resultOffset;
				}

				if (this.idBlock.error.length === 0) this.blockLength += this.idBlock.blockLength;

				if (this.lenBlock.error.length === 0) this.blockLength += this.lenBlock.blockLength;

				if (this.valueBlock.error.length === 0) this.blockLength += this.valueBlock.blockLength;

				return resultOffset;
			}
		}, {
			key: 'toBER',
			value: function toBER() {
				var sizeOnly = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : false;

				var retBuf = void 0;

				var idBlockBuf = this.idBlock.toBER(sizeOnly);
				var valueBlockSizeBuf = this.valueBlock.toBER(true);

				this.lenBlock.length = valueBlockSizeBuf.byteLength;
				var lenBlockBuf = this.lenBlock.toBER(sizeOnly);

				retBuf = utilConcatBuf(idBlockBuf, lenBlockBuf);

				var valueBlockBuf = void 0;

				if (sizeOnly === false) valueBlockBuf = this.valueBlock.toBER(sizeOnly);else valueBlockBuf = new ArrayBuffer(this.lenBlock.length);

				retBuf = utilConcatBuf(retBuf, valueBlockBuf);

				if (this.lenBlock.isIndefiniteForm === true) {
					var indefBuf = new ArrayBuffer(2);

					if (sizeOnly === false) {
						var indefView = new Uint8Array(indefBuf);

						indefView[0] = 0x00;
						indefView[1] = 0x00;
					}

					retBuf = utilConcatBuf(retBuf, indefBuf);
				}

				return retBuf;
			}
		}, {
			key: 'toJSON',
			value: function toJSON() {
				var object = {};

				try {
					object = _get(BaseBlock.prototype.__proto__ || Object.getPrototypeOf(BaseBlock.prototype), 'toJSON', this).call(this);
				} catch (ex) {}


				object.idBlock = this.idBlock.toJSON();
				object.lenBlock = this.lenBlock.toJSON();
				object.valueBlock = this.valueBlock.toJSON();

				if ("name" in this) object.name = this.name;
				if ("optional" in this) object.optional = this.optional;
				if ("primitiveSchema" in this) object.primitiveSchema = this.primitiveSchema.toJSON();

				return object;
			}
		}], [{
			key: 'blockName',
			value: function blockName() {
				return "BaseBlock";
			}
		}]);

		return BaseBlock;
	}(LocalBaseBlock);

	var LocalPrimitiveValueBlock = function (_LocalValueBlock) {
		_inherits(LocalPrimitiveValueBlock, _LocalValueBlock);

		function LocalPrimitiveValueBlock() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, LocalPrimitiveValueBlock);

			var _this9 = _possibleConstructorReturn(this, (LocalPrimitiveValueBlock.__proto__ || Object.getPrototypeOf(LocalPrimitiveValueBlock)).call(this, parameters));

			if ("valueHex" in parameters) _this9.valueHex = parameters.valueHex.slice(0);else _this9.valueHex = new ArrayBuffer(0);

			_this9.isHexOnly = getParametersValue(parameters, "isHexOnly", true);
			return _this9;
		}

		_createClass(LocalPrimitiveValueBlock, [{
			key: 'fromBER',
			value: function fromBER(inputBuffer, inputOffset, inputLength) {
				if (checkBufferParams(this, inputBuffer, inputOffset, inputLength) === false) return -1;

				var intBuffer = new Uint8Array(inputBuffer, inputOffset, inputLength);

				if (intBuffer.length === 0) {
					this.warnings.push("Zero buffer length");
					return inputOffset;
				}

				this.valueHex = new ArrayBuffer(intBuffer.length);
				var valueHexView = new Uint8Array(this.valueHex);

				for (var i = 0; i < intBuffer.length; i++) {
					valueHexView[i] = intBuffer[i];
				}

				this.blockLength = inputLength;

				return inputOffset + inputLength;
			}
		}, {
			key: 'toBER',
			value: function toBER() {
				var sizeOnly = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : false;

				return this.valueHex.slice(0);
			}
		}, {
			key: 'toJSON',
			value: function toJSON() {
				var object = {};

				try {
					object = _get(LocalPrimitiveValueBlock.prototype.__proto__ || Object.getPrototypeOf(LocalPrimitiveValueBlock.prototype), 'toJSON', this).call(this);
				} catch (ex) {}


				object.valueHex = bufferToHexCodes(this.valueHex, 0, this.valueHex.byteLength);
				object.isHexOnly = this.isHexOnly;

				return object;
			}
		}], [{
			key: 'blockName',
			value: function blockName() {
				return "PrimitiveValueBlock";
			}
		}]);

		return LocalPrimitiveValueBlock;
	}(LocalValueBlock);

	var Primitive = function (_BaseBlock) {
		_inherits(Primitive, _BaseBlock);

		function Primitive() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, Primitive);

			var _this10 = _possibleConstructorReturn(this, (Primitive.__proto__ || Object.getPrototypeOf(Primitive)).call(this, parameters, LocalPrimitiveValueBlock));

			_this10.idBlock.isConstructed = false;
			return _this10;
		}

		_createClass(Primitive, null, [{
			key: 'blockName',
			value: function blockName() {
				return "PRIMITIVE";
			}
		}]);

		return Primitive;
	}(BaseBlock);

	var LocalConstructedValueBlock = function (_LocalValueBlock2) {
		_inherits(LocalConstructedValueBlock, _LocalValueBlock2);

		function LocalConstructedValueBlock() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, LocalConstructedValueBlock);

			var _this11 = _possibleConstructorReturn(this, (LocalConstructedValueBlock.__proto__ || Object.getPrototypeOf(LocalConstructedValueBlock)).call(this, parameters));

			_this11.value = getParametersValue(parameters, "value", []);
			_this11.isIndefiniteForm = getParametersValue(parameters, "isIndefiniteForm", false);
			return _this11;
		}

		_createClass(LocalConstructedValueBlock, [{
			key: 'fromBER',
			value: function fromBER(inputBuffer, inputOffset, inputLength) {
				var initialOffset = inputOffset;
				var initialLength = inputLength;

				if (checkBufferParams(this, inputBuffer, inputOffset, inputLength) === false) return -1;

				var intBuffer = new Uint8Array(inputBuffer, inputOffset, inputLength);

				if (intBuffer.length === 0) {
					this.warnings.push("Zero buffer length");
					return inputOffset;
				}

				function checkLen(indefiniteLength, length) {
					if (indefiniteLength === true) return 1;

					return length;
				}


				var currentOffset = inputOffset;

				while (checkLen(this.isIndefiniteForm, inputLength) > 0) {
					var returnObject = LocalFromBER(inputBuffer, currentOffset, inputLength);
					if (returnObject.offset === -1) {
						this.error = returnObject.result.error;
						this.warnings.concat(returnObject.result.warnings);
						return -1;
					}

					currentOffset = returnObject.offset;

					this.blockLength += returnObject.result.blockLength;
					inputLength -= returnObject.result.blockLength;

					this.value.push(returnObject.result);

					if (this.isIndefiniteForm === true && returnObject.result.constructor.blockName() === EndOfContent.blockName()) break;
				}

				if (this.isIndefiniteForm === true) {
					if (this.value[this.value.length - 1].constructor.blockName() === EndOfContent.blockName()) this.value.pop();else this.warnings.push("No EndOfContent block encoded");
				}

				this.valueBeforeDecode = inputBuffer.slice(initialOffset, initialOffset + initialLength);


				return currentOffset;
			}
		}, {
			key: 'toBER',
			value: function toBER() {
				var sizeOnly = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : false;

				var retBuf = new ArrayBuffer(0);

				for (var i = 0; i < this.value.length; i++) {
					var valueBuf = this.value[i].toBER(sizeOnly);
					retBuf = utilConcatBuf(retBuf, valueBuf);
				}

				return retBuf;
			}
		}, {
			key: 'toJSON',
			value: function toJSON() {
				var object = {};

				try {
					object = _get(LocalConstructedValueBlock.prototype.__proto__ || Object.getPrototypeOf(LocalConstructedValueBlock.prototype), 'toJSON', this).call(this);
				} catch (ex) {}


				object.isIndefiniteForm = this.isIndefiniteForm;
				object.value = [];
				for (var i = 0; i < this.value.length; i++) {
					object.value.push(this.value[i].toJSON());
				}return object;
			}
		}], [{
			key: 'blockName',
			value: function blockName() {
				return "ConstructedValueBlock";
			}
		}]);

		return LocalConstructedValueBlock;
	}(LocalValueBlock);

	var Constructed = function (_BaseBlock2) {
		_inherits(Constructed, _BaseBlock2);

		function Constructed() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, Constructed);

			var _this12 = _possibleConstructorReturn(this, (Constructed.__proto__ || Object.getPrototypeOf(Constructed)).call(this, parameters, LocalConstructedValueBlock));

			_this12.idBlock.isConstructed = true;
			return _this12;
		}

		_createClass(Constructed, [{
			key: 'fromBER',
			value: function fromBER(inputBuffer, inputOffset, inputLength) {
				this.valueBlock.isIndefiniteForm = this.lenBlock.isIndefiniteForm;

				var resultOffset = this.valueBlock.fromBER(inputBuffer, inputOffset, this.lenBlock.isIndefiniteForm === true ? inputLength : this.lenBlock.length);
				if (resultOffset === -1) {
					this.error = this.valueBlock.error;
					return resultOffset;
				}

				if (this.idBlock.error.length === 0) this.blockLength += this.idBlock.blockLength;

				if (this.lenBlock.error.length === 0) this.blockLength += this.lenBlock.blockLength;

				if (this.valueBlock.error.length === 0) this.blockLength += this.valueBlock.blockLength;

				return resultOffset;
			}
		}], [{
			key: 'blockName',
			value: function blockName() {
				return "CONSTRUCTED";
			}
		}]);

		return Constructed;
	}(BaseBlock);

	var LocalEndOfContentValueBlock = function (_LocalValueBlock3) {
		_inherits(LocalEndOfContentValueBlock, _LocalValueBlock3);

		function LocalEndOfContentValueBlock() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, LocalEndOfContentValueBlock);

			return _possibleConstructorReturn(this, (LocalEndOfContentValueBlock.__proto__ || Object.getPrototypeOf(LocalEndOfContentValueBlock)).call(this, parameters));
		}

		_createClass(LocalEndOfContentValueBlock, [{
			key: 'fromBER',
			value: function fromBER(inputBuffer, inputOffset, inputLength) {
				return inputOffset;
			}
		}, {
			key: 'toBER',
			value: function toBER() {
				var sizeOnly = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : false;

				return new ArrayBuffer(0);
			}
		}], [{
			key: 'blockName',
			value: function blockName() {
				return "EndOfContentValueBlock";
			}
		}]);

		return LocalEndOfContentValueBlock;
	}(LocalValueBlock);

	var EndOfContent = function (_BaseBlock3) {
		_inherits(EndOfContent, _BaseBlock3);

		function EndOfContent() {
			var paramaters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, EndOfContent);

			var _this14 = _possibleConstructorReturn(this, (EndOfContent.__proto__ || Object.getPrototypeOf(EndOfContent)).call(this, paramaters, LocalEndOfContentValueBlock));

			_this14.idBlock.tagClass = 1;
			_this14.idBlock.tagNumber = 0;return _this14;
		}

		_createClass(EndOfContent, null, [{
			key: 'blockName',
			value: function blockName() {
				return "EndOfContent";
			}
		}]);

		return EndOfContent;
	}(BaseBlock);

	var LocalBooleanValueBlock = function (_LocalValueBlock4) {
		_inherits(LocalBooleanValueBlock, _LocalValueBlock4);

		function LocalBooleanValueBlock() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, LocalBooleanValueBlock);

			var _this15 = _possibleConstructorReturn(this, (LocalBooleanValueBlock.__proto__ || Object.getPrototypeOf(LocalBooleanValueBlock)).call(this, parameters));

			_this15.value = getParametersValue(parameters, "value", false);
			_this15.isHexOnly = getParametersValue(parameters, "isHexOnly", false);

			if ("valueHex" in parameters) _this15.valueHex = parameters.valueHex.slice(0);else {
				_this15.valueHex = new ArrayBuffer(1);
				if (_this15.value === true) {
					var view = new Uint8Array(_this15.valueHex);
					view[0] = 0xFF;
				}
			}
			return _this15;
		}

		_createClass(LocalBooleanValueBlock, [{
			key: 'fromBER',
			value: function fromBER(inputBuffer, inputOffset, inputLength) {
				if (checkBufferParams(this, inputBuffer, inputOffset, inputLength) === false) return -1;

				var intBuffer = new Uint8Array(inputBuffer, inputOffset, inputLength);


				if (inputLength > 1) this.warnings.push("Boolean value encoded in more then 1 octet");

				this.value = intBuffer[0] !== 0x00;

				this.isHexOnly = true;

				this.valueHex = new ArrayBuffer(intBuffer.length);
				var view = new Uint8Array(this.valueHex);

				for (var i = 0; i < intBuffer.length; i++) {
					view[i] = intBuffer[i];
				}

				this.blockLength = inputLength;

				return inputOffset + inputLength;
			}
		}, {
			key: 'toBER',
			value: function toBER() {
				var sizeOnly = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : false;

				return this.valueHex;
			}
		}, {
			key: 'toJSON',
			value: function toJSON() {
				var object = {};

				try {
					object = _get(LocalBooleanValueBlock.prototype.__proto__ || Object.getPrototypeOf(LocalBooleanValueBlock.prototype), 'toJSON', this).call(this);
				} catch (ex) {}


				object.value = this.value;
				object.isHexOnly = this.isHexOnly;
				object.valueHex = bufferToHexCodes(this.valueHex, 0, this.valueHex.byteLength);

				return object;
			}
		}], [{
			key: 'blockName',
			value: function blockName() {
				return "BooleanValueBlock";
			}
		}]);

		return LocalBooleanValueBlock;
	}(LocalValueBlock);

	var Boolean = function (_BaseBlock4) {
		_inherits(Boolean, _BaseBlock4);

		function Boolean() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, Boolean);

			var _this16 = _possibleConstructorReturn(this, (Boolean.__proto__ || Object.getPrototypeOf(Boolean)).call(this, parameters, LocalBooleanValueBlock));

			_this16.idBlock.tagClass = 1;
			_this16.idBlock.tagNumber = 1;return _this16;
		}

		_createClass(Boolean, null, [{
			key: 'blockName',
			value: function blockName() {
				return "Boolean";
			}
		}]);

		return Boolean;
	}(BaseBlock);

	var Sequence = function (_Constructed) {
		_inherits(Sequence, _Constructed);

		function Sequence() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, Sequence);

			var _this17 = _possibleConstructorReturn(this, (Sequence.__proto__ || Object.getPrototypeOf(Sequence)).call(this, parameters));

			_this17.idBlock.tagClass = 1;
			_this17.idBlock.tagNumber = 16;return _this17;
		}

		_createClass(Sequence, null, [{
			key: 'blockName',
			value: function blockName() {
				return "Sequence";
			}
		}]);

		return Sequence;
	}(Constructed);

	var Set = function (_Constructed2) {
		_inherits(Set, _Constructed2);

		function Set() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, Set);

			var _this18 = _possibleConstructorReturn(this, (Set.__proto__ || Object.getPrototypeOf(Set)).call(this, parameters));

			_this18.idBlock.tagClass = 1;
			_this18.idBlock.tagNumber = 17;return _this18;
		}

		_createClass(Set, null, [{
			key: 'blockName',
			value: function blockName() {
				return "Set";
			}
		}]);

		return Set;
	}(Constructed);

	var Null = function (_BaseBlock5) {
		_inherits(Null, _BaseBlock5);

		function Null() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, Null);

			var _this19 = _possibleConstructorReturn(this, (Null.__proto__ || Object.getPrototypeOf(Null)).call(this, parameters, LocalBaseBlock));

			_this19.idBlock.tagClass = 1;
			_this19.idBlock.tagNumber = 5;return _this19;
		}

		_createClass(Null, [{
			key: 'fromBER',
			value: function fromBER(inputBuffer, inputOffset, inputLength) {
				if (this.lenBlock.length > 0) this.warnings.push("Non-zero length of value block for Null type");

				if (this.idBlock.error.length === 0) this.blockLength += this.idBlock.blockLength;

				if (this.lenBlock.error.length === 0) this.blockLength += this.lenBlock.blockLength;

				this.blockLength += inputLength;

				return inputOffset + inputLength;
			}
		}, {
			key: 'toBER',
			value: function toBER() {
				var sizeOnly = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : false;

				var retBuf = new ArrayBuffer(2);

				if (sizeOnly === true) return retBuf;

				var retView = new Uint8Array(retBuf);
				retView[0] = 0x05;
				retView[1] = 0x00;

				return retBuf;
			}
		}], [{
			key: 'blockName',
			value: function blockName() {
				return "Null";
			}
		}]);

		return Null;
	}(BaseBlock);

	var LocalOctetStringValueBlock = function (_LocalHexBlock2) {
		_inherits(LocalOctetStringValueBlock, _LocalHexBlock2);

		function LocalOctetStringValueBlock() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, LocalOctetStringValueBlock);

			var _this20 = _possibleConstructorReturn(this, (LocalOctetStringValueBlock.__proto__ || Object.getPrototypeOf(LocalOctetStringValueBlock)).call(this, parameters));

			_this20.isConstructed = getParametersValue(parameters, "isConstructed", false);
			return _this20;
		}

		_createClass(LocalOctetStringValueBlock, [{
			key: 'fromBER',
			value: function fromBER(inputBuffer, inputOffset, inputLength) {
				var resultOffset = 0;

				if (this.isConstructed === true) {
					this.isHexOnly = false;

					resultOffset = LocalConstructedValueBlock.prototype.fromBER.call(this, inputBuffer, inputOffset, inputLength);
					if (resultOffset === -1) return resultOffset;

					for (var i = 0; i < this.value.length; i++) {
						var currentBlockName = this.value[i].constructor.blockName();

						if (currentBlockName === EndOfContent.blockName()) {
							if (this.isIndefiniteForm === true) break;else {
								this.error = "EndOfContent is unexpected, OCTET STRING may consists of OCTET STRINGs only";
								return -1;
							}
						}

						if (currentBlockName !== OctetString.blockName()) {
							this.error = "OCTET STRING may consists of OCTET STRINGs only";
							return -1;
						}
					}
				} else {
					this.isHexOnly = true;

					resultOffset = _get(LocalOctetStringValueBlock.prototype.__proto__ || Object.getPrototypeOf(LocalOctetStringValueBlock.prototype), 'fromBER', this).call(this, inputBuffer, inputOffset, inputLength);
					this.blockLength = inputLength;
				}

				return resultOffset;
			}
		}, {
			key: 'toBER',
			value: function toBER() {
				var sizeOnly = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : false;

				if (this.isConstructed === true) return LocalConstructedValueBlock.prototype.toBER.call(this, sizeOnly);

				var retBuf = new ArrayBuffer(this.valueHex.byteLength);

				if (sizeOnly === true) return retBuf;

				if (this.valueHex.byteLength === 0) return retBuf;

				retBuf = this.valueHex.slice(0);

				return retBuf;
			}
		}, {
			key: 'toJSON',
			value: function toJSON() {
				var object = {};

				try {
					object = _get(LocalOctetStringValueBlock.prototype.__proto__ || Object.getPrototypeOf(LocalOctetStringValueBlock.prototype), 'toJSON', this).call(this);
				} catch (ex) {}


				object.isConstructed = this.isConstructed;
				object.isHexOnly = this.isHexOnly;
				object.valueHex = bufferToHexCodes(this.valueHex, 0, this.valueHex.byteLength);

				return object;
			}
		}], [{
			key: 'blockName',
			value: function blockName() {
				return "OctetStringValueBlock";
			}
		}]);

		return LocalOctetStringValueBlock;
	}(LocalHexBlock(LocalConstructedValueBlock));

	var OctetString = function (_BaseBlock6) {
		_inherits(OctetString, _BaseBlock6);

		function OctetString() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, OctetString);

			var _this21 = _possibleConstructorReturn(this, (OctetString.__proto__ || Object.getPrototypeOf(OctetString)).call(this, parameters, LocalOctetStringValueBlock));

			_this21.idBlock.tagClass = 1;
			_this21.idBlock.tagNumber = 4;return _this21;
		}

		_createClass(OctetString, [{
			key: 'fromBER',
			value: function fromBER(inputBuffer, inputOffset, inputLength) {
				this.valueBlock.isConstructed = this.idBlock.isConstructed;
				this.valueBlock.isIndefiniteForm = this.lenBlock.isIndefiniteForm;

				if (inputLength === 0) {
					if (this.idBlock.error.length === 0) this.blockLength += this.idBlock.blockLength;

					if (this.lenBlock.error.length === 0) this.blockLength += this.lenBlock.blockLength;

					return inputOffset;
				}


				return _get(OctetString.prototype.__proto__ || Object.getPrototypeOf(OctetString.prototype), 'fromBER', this).call(this, inputBuffer, inputOffset, inputLength);
			}
		}, {
			key: 'isEqual',
			value: function isEqual(octetString) {
				if (octetString instanceof OctetString === false) return false;

				if (JSON.stringify(this) !== JSON.stringify(octetString)) return false;


				return true;
			}
		}], [{
			key: 'blockName',
			value: function blockName() {
				return "OctetString";
			}
		}]);

		return OctetString;
	}(BaseBlock);

	var LocalBitStringValueBlock = function (_LocalHexBlock3) {
		_inherits(LocalBitStringValueBlock, _LocalHexBlock3);

		function LocalBitStringValueBlock() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, LocalBitStringValueBlock);

			var _this22 = _possibleConstructorReturn(this, (LocalBitStringValueBlock.__proto__ || Object.getPrototypeOf(LocalBitStringValueBlock)).call(this, parameters));

			_this22.unusedBits = getParametersValue(parameters, "unusedBits", 0);
			_this22.isConstructed = getParametersValue(parameters, "isConstructed", false);
			_this22.blockLength = _this22.valueHex.byteLength + 1;return _this22;
		}

		_createClass(LocalBitStringValueBlock, [{
			key: 'fromBER',
			value: function fromBER(inputBuffer, inputOffset, inputLength) {
				if (inputLength === 0) return inputOffset;


				var resultOffset = -1;

				if (this.isConstructed === true) {
					resultOffset = LocalConstructedValueBlock.prototype.fromBER.call(this, inputBuffer, inputOffset, inputLength);
					if (resultOffset === -1) return resultOffset;

					for (var i = 0; i < this.value.length; i++) {
						var currentBlockName = this.value[i].constructor.blockName();

						if (currentBlockName === EndOfContent.blockName()) {
							if (this.isIndefiniteForm === true) break;else {
								this.error = "EndOfContent is unexpected, BIT STRING may consists of BIT STRINGs only";
								return -1;
							}
						}

						if (currentBlockName !== BitString.blockName()) {
							this.error = "BIT STRING may consists of BIT STRINGs only";
							return -1;
						}

						if (this.unusedBits > 0 && this.value[i].unusedBits > 0) {
							this.error = "Usign of \"unused bits\" inside constructive BIT STRING allowed for least one only";
							return -1;
						}

						this.unusedBits = this.value[i].unusedBits;
						if (this.unusedBits > 7) {
							this.error = "Unused bits for BitString must be in range 0-7";
							return -1;
						}
					}

					return resultOffset;
				}

				if (checkBufferParams(this, inputBuffer, inputOffset, inputLength) === false) return -1;


				var intBuffer = new Uint8Array(inputBuffer, inputOffset, inputLength);

				this.unusedBits = intBuffer[0];
				if (this.unusedBits > 7) {
					this.error = "Unused bits for BitString must be in range 0-7";
					return -1;
				}

				this.valueHex = new ArrayBuffer(intBuffer.length - 1);
				var view = new Uint8Array(this.valueHex);
				for (var _i3 = 0; _i3 < inputLength - 1; _i3++) {
					view[_i3] = intBuffer[_i3 + 1];
				}

				this.blockLength = intBuffer.length;

				return inputOffset + inputLength;
			}
		}, {
			key: 'toBER',
			value: function toBER() {
				var sizeOnly = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : false;

				if (this.isConstructed === true) return LocalConstructedValueBlock.prototype.toBER.call(this, sizeOnly);

				if (sizeOnly === true) return new ArrayBuffer(this.valueHex.byteLength + 1);

				if (this.valueHex.byteLength === 0) return new ArrayBuffer(0);

				var curView = new Uint8Array(this.valueHex);

				var retBuf = new ArrayBuffer(this.valueHex.byteLength + 1);
				var retView = new Uint8Array(retBuf);

				retView[0] = this.unusedBits;

				for (var i = 0; i < this.valueHex.byteLength; i++) {
					retView[i + 1] = curView[i];
				}return retBuf;
			}
		}, {
			key: 'toJSON',
			value: function toJSON() {
				var object = {};

				try {
					object = _get(LocalBitStringValueBlock.prototype.__proto__ || Object.getPrototypeOf(LocalBitStringValueBlock.prototype), 'toJSON', this).call(this);
				} catch (ex) {}


				object.unusedBits = this.unusedBits;
				object.isConstructed = this.isConstructed;
				object.isHexOnly = this.isHexOnly;
				object.valueHex = bufferToHexCodes(this.valueHex, 0, this.valueHex.byteLength);

				return object;
			}
		}], [{
			key: 'blockName',
			value: function blockName() {
				return "BitStringValueBlock";
			}
		}]);

		return LocalBitStringValueBlock;
	}(LocalHexBlock(LocalConstructedValueBlock));

	var BitString = function (_BaseBlock7) {
		_inherits(BitString, _BaseBlock7);

		function BitString() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, BitString);

			var _this23 = _possibleConstructorReturn(this, (BitString.__proto__ || Object.getPrototypeOf(BitString)).call(this, parameters, LocalBitStringValueBlock));

			_this23.idBlock.tagClass = 1;
			_this23.idBlock.tagNumber = 3;return _this23;
		}

		_createClass(BitString, [{
			key: 'fromBER',
			value: function fromBER(inputBuffer, inputOffset, inputLength) {
				if (inputLength === 0) return inputOffset;


				this.valueBlock.isConstructed = this.idBlock.isConstructed;
				this.valueBlock.isIndefiniteForm = this.lenBlock.isIndefiniteForm;

				return _get(BitString.prototype.__proto__ || Object.getPrototypeOf(BitString.prototype), 'fromBER', this).call(this, inputBuffer, inputOffset, inputLength);
			}
		}, {
			key: 'isEqual',
			value: function isEqual(bitString) {
				if (bitString instanceof BitString === false) return false;

				if (JSON.stringify(this) !== JSON.stringify(bitString)) return false;


				return true;
			}
		}], [{
			key: 'blockName',
			value: function blockName() {
				return "BitString";
			}
		}]);

		return BitString;
	}(BaseBlock);

	var LocalIntegerValueBlock = function (_LocalHexBlock4) {
		_inherits(LocalIntegerValueBlock, _LocalHexBlock4);

		function LocalIntegerValueBlock() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, LocalIntegerValueBlock);

			var _this24 = _possibleConstructorReturn(this, (LocalIntegerValueBlock.__proto__ || Object.getPrototypeOf(LocalIntegerValueBlock)).call(this, parameters));

			if ("value" in parameters) _this24.valueDec = parameters.value;
			return _this24;
		}

		_createClass(LocalIntegerValueBlock, [{
			key: 'fromDER',
			value: function fromDER(inputBuffer, inputOffset, inputLength) {
				var expectedLength = arguments.length > 3 && arguments[3] !== undefined ? arguments[3] : 0;

				var offset = this.fromBER(inputBuffer, inputOffset, inputLength);
				if (offset === -1) return offset;

				var view = new Uint8Array(this._valueHex);

				if (view[0] === 0x00 && (view[1] & 0x80) !== 0) {
					var updatedValueHex = new ArrayBuffer(this._valueHex.byteLength - 1);
					var updatedView = new Uint8Array(updatedValueHex);

					updatedView.set(new Uint8Array(this._valueHex, 1, this._valueHex.byteLength - 1));

					this._valueHex = updatedValueHex.slice(0);
				} else {
					if (expectedLength !== 0) {
						if (this._valueHex.byteLength < expectedLength) {
							if (expectedLength - this._valueHex.byteLength > 1) expectedLength = this._valueHex.byteLength + 1;

							var _updatedValueHex = new ArrayBuffer(expectedLength);
							var _updatedView = new Uint8Array(_updatedValueHex);

							_updatedView.set(view, expectedLength - this._valueHex.byteLength);

							this._valueHex = _updatedValueHex.slice(0);
						}
					}
				}

				return offset;
			}
		}, {
			key: 'toDER',
			value: function toDER() {
				var sizeOnly = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : false;

				var view = new Uint8Array(this._valueHex);

				switch (true) {
					case (view[0] & 0x80) !== 0:
						{
							var updatedValueHex = new ArrayBuffer(this._valueHex.byteLength + 1);
							var updatedView = new Uint8Array(updatedValueHex);

							updatedView[0] = 0x00;
							updatedView.set(view, 1);

							this._valueHex = updatedValueHex.slice(0);
						}
						break;
					case view[0] === 0x00 && (view[1] & 0x80) === 0:
						{
							var _updatedValueHex2 = new ArrayBuffer(this._valueHex.byteLength - 1);
							var _updatedView2 = new Uint8Array(_updatedValueHex2);

							_updatedView2.set(new Uint8Array(this._valueHex, 1, this._valueHex.byteLength - 1));

							this._valueHex = _updatedValueHex2.slice(0);
						}
						break;
					default:
				}

				return this.toBER(sizeOnly);
			}
		}, {
			key: 'fromBER',
			value: function fromBER(inputBuffer, inputOffset, inputLength) {
				var resultOffset = _get(LocalIntegerValueBlock.prototype.__proto__ || Object.getPrototypeOf(LocalIntegerValueBlock.prototype), 'fromBER', this).call(this, inputBuffer, inputOffset, inputLength);
				if (resultOffset === -1) return resultOffset;

				this.blockLength = inputLength;

				return inputOffset + inputLength;
			}
		}, {
			key: 'toBER',
			value: function toBER() {
				var sizeOnly = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : false;

				return this.valueHex.slice(0);
			}
		}, {
			key: 'toJSON',
			value: function toJSON() {
				var object = {};

				try {
					object = _get(LocalIntegerValueBlock.prototype.__proto__ || Object.getPrototypeOf(LocalIntegerValueBlock.prototype), 'toJSON', this).call(this);
				} catch (ex) {}


				object.valueDec = this.valueDec;

				return object;
			}
		}, {
			key: 'toString',
			value: function toString() {
				function viewAdd(first, second) {
					var c = new Uint8Array([0]);

					var firstView = new Uint8Array(first);
					var secondView = new Uint8Array(second);

					var firstViewCopy = firstView.slice(0);
					var firstViewCopyLength = firstViewCopy.length - 1;
					var secondViewCopy = secondView.slice(0);
					var secondViewCopyLength = secondViewCopy.length - 1;

					var value = 0;

					var max = secondViewCopyLength < firstViewCopyLength ? firstViewCopyLength : secondViewCopyLength;

					var counter = 0;


					for (var i = max; i >= 0; i--, counter++) {
						switch (true) {
							case counter < secondViewCopy.length:
								value = firstViewCopy[firstViewCopyLength - counter] + secondViewCopy[secondViewCopyLength - counter] + c[0];
								break;
							default:
								value = firstViewCopy[firstViewCopyLength - counter] + c[0];
						}

						c[0] = value / 10;

						switch (true) {
							case counter >= firstViewCopy.length:
								firstViewCopy = utilConcatView(new Uint8Array([value % 10]), firstViewCopy);
								break;
							default:
								firstViewCopy[firstViewCopyLength - counter] = value % 10;
						}
					}

					if (c[0] > 0) firstViewCopy = utilConcatView(c, firstViewCopy);

					return firstViewCopy.slice(0);
				}

				function power2(n) {
					if (n >= powers2.length) {
						for (var p = powers2.length; p <= n; p++) {
							var c = new Uint8Array([0]);
							var _digits = powers2[p - 1].slice(0);

							for (var i = _digits.length - 1; i >= 0; i--) {
								var newValue = new Uint8Array([(_digits[i] << 1) + c[0]]);
								c[0] = newValue[0] / 10;
								_digits[i] = newValue[0] % 10;
							}

							if (c[0] > 0) _digits = utilConcatView(c, _digits);

							powers2.push(_digits);
						}
					}

					return powers2[n];
				}

				function viewSub(first, second) {
					var b = 0;

					var firstView = new Uint8Array(first);
					var secondView = new Uint8Array(second);

					var firstViewCopy = firstView.slice(0);
					var firstViewCopyLength = firstViewCopy.length - 1;
					var secondViewCopy = secondView.slice(0);
					var secondViewCopyLength = secondViewCopy.length - 1;

					var value = void 0;

					var counter = 0;


					for (var i = secondViewCopyLength; i >= 0; i--, counter++) {
						value = firstViewCopy[firstViewCopyLength - counter] - secondViewCopy[secondViewCopyLength - counter] - b;

						switch (true) {
							case value < 0:
								b = 1;
								firstViewCopy[firstViewCopyLength - counter] = value + 10;
								break;
							default:
								b = 0;
								firstViewCopy[firstViewCopyLength - counter] = value;
						}
					}

					if (b > 0) {
						for (var _i4 = firstViewCopyLength - secondViewCopyLength + 1; _i4 >= 0; _i4--, counter++) {
							value = firstViewCopy[firstViewCopyLength - counter] - b;

							if (value < 0) {
								b = 1;
								firstViewCopy[firstViewCopyLength - counter] = value + 10;
							} else {
								b = 0;
								firstViewCopy[firstViewCopyLength - counter] = value;
								break;
							}
						}
					}

					return firstViewCopy.slice();
				}

				var firstBit = this._valueHex.byteLength * 8 - 1;

				var digits = new Uint8Array(this._valueHex.byteLength * 8 / 3);
				var bitNumber = 0;
				var currentByte = void 0;

				var asn1View = new Uint8Array(this._valueHex);

				var result = "";

				var flag = false;

				for (var byteNumber = this._valueHex.byteLength - 1; byteNumber >= 0; byteNumber--) {
					currentByte = asn1View[byteNumber];

					for (var i = 0; i < 8; i++) {
						if ((currentByte & 1) === 1) {
							switch (bitNumber) {
								case firstBit:
									digits = viewSub(power2(bitNumber), digits);
									result = "-";
									break;
								default:
									digits = viewAdd(digits, power2(bitNumber));
							}
						}

						bitNumber++;
						currentByte >>= 1;
					}
				}

				for (var _i5 = 0; _i5 < digits.length; _i5++) {
					if (digits[_i5]) flag = true;

					if (flag) result += digitsString.charAt(digits[_i5]);
				}

				if (flag === false) result += digitsString.charAt(0);


				return result;
			}
		}, {
			key: 'valueHex',
			set: function set(_value) {
				this._valueHex = _value.slice(0);

				if (_value.byteLength >= 4) {
					this.warnings.push("Too big Integer for decoding, hex only");
					this.isHexOnly = true;
					this._valueDec = 0;
				} else {
					this.isHexOnly = false;

					if (_value.byteLength > 0) this._valueDec = utilDecodeTC.call(this);
				}
			},
			get: function get() {
				return this._valueHex;
			}
		}, {
			key: 'valueDec',
			set: function set(_value) {
				this._valueDec = _value;

				this.isHexOnly = false;
				this._valueHex = utilEncodeTC(_value);
			},
			get: function get() {
				return this._valueDec;
			}
		}], [{
			key: 'blockName',
			value: function blockName() {
				return "IntegerValueBlock";
			}
		}]);

		return LocalIntegerValueBlock;
	}(LocalHexBlock(LocalValueBlock));

	var Integer = function (_BaseBlock8) {
		_inherits(Integer, _BaseBlock8);

		function Integer() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, Integer);

			var _this25 = _possibleConstructorReturn(this, (Integer.__proto__ || Object.getPrototypeOf(Integer)).call(this, parameters, LocalIntegerValueBlock));

			_this25.idBlock.tagClass = 1;
			_this25.idBlock.tagNumber = 2;return _this25;
		}

		_createClass(Integer, [{
			key: 'isEqual',
			value: function isEqual(otherValue) {
				if (otherValue instanceof Integer) {
					if (this.valueBlock.isHexOnly && otherValue.valueBlock.isHexOnly) return isEqualBuffer(this.valueBlock.valueHex, otherValue.valueBlock.valueHex);

					if (this.valueBlock.isHexOnly === otherValue.valueBlock.isHexOnly) return this.valueBlock.valueDec === otherValue.valueBlock.valueDec;

					return false;
				}

				if (otherValue instanceof ArrayBuffer) return isEqualBuffer(this.valueBlock.valueHex, otherValue);

				return false;
			}
		}, {
			key: 'convertToDER',
			value: function convertToDER() {
				var integer = new Integer({ valueHex: this.valueBlock.valueHex });
				integer.valueBlock.toDER();

				return integer;
			}
		}, {
			key: 'convertFromDER',
			value: function convertFromDER() {
				var expectedLength = this.valueBlock.valueHex.byteLength % 2 ? this.valueBlock.valueHex.byteLength + 1 : this.valueBlock.valueHex.byteLength;
				var integer = new Integer({ valueHex: this.valueBlock.valueHex });
				integer.valueBlock.fromDER(integer.valueBlock.valueHex, 0, integer.valueBlock.valueHex.byteLength, expectedLength);

				return integer;
			}
		}], [{
			key: 'blockName',
			value: function blockName() {
				return "Integer";
			}
		}]);

		return Integer;
	}(BaseBlock);

	var Enumerated = function (_Integer) {
		_inherits(Enumerated, _Integer);

		function Enumerated() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, Enumerated);

			var _this26 = _possibleConstructorReturn(this, (Enumerated.__proto__ || Object.getPrototypeOf(Enumerated)).call(this, parameters));

			_this26.idBlock.tagClass = 1;
			_this26.idBlock.tagNumber = 10;return _this26;
		}

		_createClass(Enumerated, null, [{
			key: 'blockName',
			value: function blockName() {
				return "Enumerated";
			}
		}]);

		return Enumerated;
	}(Integer);

	var LocalSidValueBlock = function (_LocalHexBlock5) {
		_inherits(LocalSidValueBlock, _LocalHexBlock5);

		function LocalSidValueBlock() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, LocalSidValueBlock);

			var _this27 = _possibleConstructorReturn(this, (LocalSidValueBlock.__proto__ || Object.getPrototypeOf(LocalSidValueBlock)).call(this, parameters));

			_this27.valueDec = getParametersValue(parameters, "valueDec", -1);
			_this27.isFirstSid = getParametersValue(parameters, "isFirstSid", false);
			return _this27;
		}

		_createClass(LocalSidValueBlock, [{
			key: 'fromBER',
			value: function fromBER(inputBuffer, inputOffset, inputLength) {
				if (inputLength === 0) return inputOffset;

				if (checkBufferParams(this, inputBuffer, inputOffset, inputLength) === false) return -1;


				var intBuffer = new Uint8Array(inputBuffer, inputOffset, inputLength);

				this.valueHex = new ArrayBuffer(inputLength);
				var view = new Uint8Array(this.valueHex);

				for (var i = 0; i < inputLength; i++) {
					view[i] = intBuffer[i] & 0x7F;

					this.blockLength++;

					if ((intBuffer[i] & 0x80) === 0x00) break;
				}

				var tempValueHex = new ArrayBuffer(this.blockLength);
				var tempView = new Uint8Array(tempValueHex);

				for (var _i6 = 0; _i6 < this.blockLength; _i6++) {
					tempView[_i6] = view[_i6];
				}
				this.valueHex = tempValueHex.slice(0);
				view = new Uint8Array(this.valueHex);


				if ((intBuffer[this.blockLength - 1] & 0x80) !== 0x00) {
					this.error = "End of input reached before message was fully decoded";
					return -1;
				}

				if (view[0] === 0x00) this.warnings.push("Needlessly long format of SID encoding");

				if (this.blockLength <= 8) this.valueDec = utilFromBase(view, 7);else {
					this.isHexOnly = true;
					this.warnings.push("Too big SID for decoding, hex only");
				}

				return inputOffset + this.blockLength;
			}
		}, {
			key: 'toBER',
			value: function toBER() {
				var sizeOnly = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : false;

				var retBuf = void 0;
				var retView = void 0;


				if (this.isHexOnly) {
					if (sizeOnly === true) return new ArrayBuffer(this.valueHex.byteLength);

					var curView = new Uint8Array(this.valueHex);

					retBuf = new ArrayBuffer(this.blockLength);
					retView = new Uint8Array(retBuf);

					for (var i = 0; i < this.blockLength - 1; i++) {
						retView[i] = curView[i] | 0x80;
					}retView[this.blockLength - 1] = curView[this.blockLength - 1];

					return retBuf;
				}

				var encodedBuf = utilToBase(this.valueDec, 7);
				if (encodedBuf.byteLength === 0) {
					this.error = "Error during encoding SID value";
					return new ArrayBuffer(0);
				}

				retBuf = new ArrayBuffer(encodedBuf.byteLength);

				if (sizeOnly === false) {
					var encodedView = new Uint8Array(encodedBuf);
					retView = new Uint8Array(retBuf);

					for (var _i7 = 0; _i7 < encodedBuf.byteLength - 1; _i7++) {
						retView[_i7] = encodedView[_i7] | 0x80;
					}retView[encodedBuf.byteLength - 1] = encodedView[encodedBuf.byteLength - 1];
				}

				return retBuf;
			}
		}, {
			key: 'toString',
			value: function toString() {
				var result = "";

				if (this.isHexOnly === true) result = bufferToHexCodes(this.valueHex, 0, this.valueHex.byteLength);else {
					if (this.isFirstSid) {
						var sidValue = this.valueDec;

						if (this.valueDec <= 39) result = "0.";else {
							if (this.valueDec <= 79) {
								result = "1.";
								sidValue -= 40;
							} else {
								result = "2.";
								sidValue -= 80;
							}
						}

						result = result + sidValue.toString();
					} else result = this.valueDec.toString();
				}

				return result;
			}
		}, {
			key: 'toJSON',
			value: function toJSON() {
				var object = {};

				try {
					object = _get(LocalSidValueBlock.prototype.__proto__ || Object.getPrototypeOf(LocalSidValueBlock.prototype), 'toJSON', this).call(this);
				} catch (ex) {}


				object.valueDec = this.valueDec;
				object.isFirstSid = this.isFirstSid;

				return object;
			}
		}], [{
			key: 'blockName',
			value: function blockName() {
				return "sidBlock";
			}
		}]);

		return LocalSidValueBlock;
	}(LocalHexBlock(LocalBaseBlock));

	var LocalObjectIdentifierValueBlock = function (_LocalValueBlock5) {
		_inherits(LocalObjectIdentifierValueBlock, _LocalValueBlock5);

		function LocalObjectIdentifierValueBlock() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, LocalObjectIdentifierValueBlock);

			var _this28 = _possibleConstructorReturn(this, (LocalObjectIdentifierValueBlock.__proto__ || Object.getPrototypeOf(LocalObjectIdentifierValueBlock)).call(this, parameters));

			_this28.fromString(getParametersValue(parameters, "value", ""));
			return _this28;
		}

		_createClass(LocalObjectIdentifierValueBlock, [{
			key: 'fromBER',
			value: function fromBER(inputBuffer, inputOffset, inputLength) {
				var resultOffset = inputOffset;

				while (inputLength > 0) {
					var sidBlock = new LocalSidValueBlock();
					resultOffset = sidBlock.fromBER(inputBuffer, resultOffset, inputLength);
					if (resultOffset === -1) {
						this.blockLength = 0;
						this.error = sidBlock.error;
						return resultOffset;
					}

					if (this.value.length === 0) sidBlock.isFirstSid = true;

					this.blockLength += sidBlock.blockLength;
					inputLength -= sidBlock.blockLength;

					this.value.push(sidBlock);
				}

				return resultOffset;
			}
		}, {
			key: 'toBER',
			value: function toBER() {
				var sizeOnly = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : false;

				var retBuf = new ArrayBuffer(0);

				for (var i = 0; i < this.value.length; i++) {
					var valueBuf = this.value[i].toBER(sizeOnly);
					if (valueBuf.byteLength === 0) {
						this.error = this.value[i].error;
						return new ArrayBuffer(0);
					}

					retBuf = utilConcatBuf(retBuf, valueBuf);
				}

				return retBuf;
			}
		}, {
			key: 'fromString',
			value: function fromString(string) {
				this.value = [];

				var pos1 = 0;
				var pos2 = 0;

				var sid = "";

				var flag = false;

				do {
					pos2 = string.indexOf(".", pos1);
					if (pos2 === -1) sid = string.substr(pos1);else sid = string.substr(pos1, pos2 - pos1);

					pos1 = pos2 + 1;

					if (flag) {
						var sidBlock = this.value[0];

						var plus = 0;

						switch (sidBlock.valueDec) {
							case 0:
								break;
							case 1:
								plus = 40;
								break;
							case 2:
								plus = 80;
								break;
							default:
								this.value = [];
								return false;}

						var parsedSID = parseInt(sid, 10);
						if (isNaN(parsedSID)) return true;

						sidBlock.valueDec = parsedSID + plus;

						flag = false;
					} else {
						var _sidBlock = new LocalSidValueBlock();
						_sidBlock.valueDec = parseInt(sid, 10);
						if (isNaN(_sidBlock.valueDec)) return true;

						if (this.value.length === 0) {
							_sidBlock.isFirstSid = true;
							flag = true;
						}

						this.value.push(_sidBlock);
					}
				} while (pos2 !== -1);

				return true;
			}
		}, {
			key: 'toString',
			value: function toString() {
				var result = "";
				var isHexOnly = false;

				for (var i = 0; i < this.value.length; i++) {
					isHexOnly = this.value[i].isHexOnly;

					var sidStr = this.value[i].toString();

					if (i !== 0) result = result + '.';

					if (isHexOnly) {
						sidStr = '{' + sidStr + '}';

						if (this.value[i].isFirstSid) result = '2.{' + sidStr + ' - 80}';else result = result + sidStr;
					} else result = result + sidStr;
				}

				return result;
			}
		}, {
			key: 'toJSON',
			value: function toJSON() {
				var object = {};

				try {
					object = _get(LocalObjectIdentifierValueBlock.prototype.__proto__ || Object.getPrototypeOf(LocalObjectIdentifierValueBlock.prototype), 'toJSON', this).call(this);
				} catch (ex) {}


				object.value = this.toString();
				object.sidArray = [];
				for (var i = 0; i < this.value.length; i++) {
					object.sidArray.push(this.value[i].toJSON());
				}return object;
			}
		}], [{
			key: 'blockName',
			value: function blockName() {
				return "ObjectIdentifierValueBlock";
			}
		}]);

		return LocalObjectIdentifierValueBlock;
	}(LocalValueBlock);

	var ObjectIdentifier$1 = function (_BaseBlock9) {
		_inherits(ObjectIdentifier$1, _BaseBlock9);

		function ObjectIdentifier$1() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, ObjectIdentifier$1);

			var _this29 = _possibleConstructorReturn(this, (ObjectIdentifier$1.__proto__ || Object.getPrototypeOf(ObjectIdentifier$1)).call(this, parameters, LocalObjectIdentifierValueBlock));

			_this29.idBlock.tagClass = 1;
			_this29.idBlock.tagNumber = 6;return _this29;
		}

		_createClass(ObjectIdentifier$1, null, [{
			key: 'blockName',
			value: function blockName() {
				return "ObjectIdentifier";
			}
		}]);

		return ObjectIdentifier$1;
	}(BaseBlock);

	var LocalUtf8StringValueBlock = function (_LocalHexBlock6) {
		_inherits(LocalUtf8StringValueBlock, _LocalHexBlock6);

		function LocalUtf8StringValueBlock() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, LocalUtf8StringValueBlock);

			var _this30 = _possibleConstructorReturn(this, (LocalUtf8StringValueBlock.__proto__ || Object.getPrototypeOf(LocalUtf8StringValueBlock)).call(this, parameters));

			_this30.isHexOnly = true;
			_this30.value = "";return _this30;
		}

		_createClass(LocalUtf8StringValueBlock, [{
			key: 'toJSON',
			value: function toJSON() {
				var object = {};

				try {
					object = _get(LocalUtf8StringValueBlock.prototype.__proto__ || Object.getPrototypeOf(LocalUtf8StringValueBlock.prototype), 'toJSON', this).call(this);
				} catch (ex) {}


				object.value = this.value;

				return object;
			}
		}], [{
			key: 'blockName',
			value: function blockName() {
				return "Utf8StringValueBlock";
			}
		}]);

		return LocalUtf8StringValueBlock;
	}(LocalHexBlock(LocalBaseBlock));

	var Utf8String = function (_BaseBlock10) {
		_inherits(Utf8String, _BaseBlock10);

		function Utf8String() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, Utf8String);

			var _this31 = _possibleConstructorReturn(this, (Utf8String.__proto__ || Object.getPrototypeOf(Utf8String)).call(this, parameters, LocalUtf8StringValueBlock));

			if ("value" in parameters) _this31.fromString(parameters.value);

			_this31.idBlock.tagClass = 1;
			_this31.idBlock.tagNumber = 12;return _this31;
		}

		_createClass(Utf8String, [{
			key: 'fromBER',
			value: function fromBER(inputBuffer, inputOffset, inputLength) {
				var resultOffset = this.valueBlock.fromBER(inputBuffer, inputOffset, this.lenBlock.isIndefiniteForm === true ? inputLength : this.lenBlock.length);
				if (resultOffset === -1) {
					this.error = this.valueBlock.error;
					return resultOffset;
				}

				this.fromBuffer(this.valueBlock.valueHex);

				if (this.idBlock.error.length === 0) this.blockLength += this.idBlock.blockLength;

				if (this.lenBlock.error.length === 0) this.blockLength += this.lenBlock.blockLength;

				if (this.valueBlock.error.length === 0) this.blockLength += this.valueBlock.blockLength;

				return resultOffset;
			}
		}, {
			key: 'fromBuffer',
			value: function fromBuffer(inputBuffer) {
				this.valueBlock.value = String.fromCharCode.apply(null, new Uint8Array(inputBuffer));

				try {
					this.valueBlock.value = decodeURIComponent(escape(this.valueBlock.value));
				} catch (ex) {
					this.warnings.push('Error during "decodeURIComponent": ' + ex + ', using raw string');
				}
			}
		}, {
			key: 'fromString',
			value: function fromString(inputString) {
				var str = unescape(encodeURIComponent(inputString));
				var strLen = str.length;

				this.valueBlock.valueHex = new ArrayBuffer(strLen);
				var view = new Uint8Array(this.valueBlock.valueHex);

				for (var i = 0; i < strLen; i++) {
					view[i] = str.charCodeAt(i);
				}this.valueBlock.value = inputString;
			}
		}], [{
			key: 'blockName',
			value: function blockName() {
				return "Utf8String";
			}
		}]);

		return Utf8String;
	}(BaseBlock);

	var LocalBmpStringValueBlock = function (_LocalHexBlock7) {
		_inherits(LocalBmpStringValueBlock, _LocalHexBlock7);

		function LocalBmpStringValueBlock() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, LocalBmpStringValueBlock);

			var _this32 = _possibleConstructorReturn(this, (LocalBmpStringValueBlock.__proto__ || Object.getPrototypeOf(LocalBmpStringValueBlock)).call(this, parameters));

			_this32.isHexOnly = true;
			_this32.value = "";
			return _this32;
		}

		_createClass(LocalBmpStringValueBlock, [{
			key: 'toJSON',
			value: function toJSON() {
				var object = {};

				try {
					object = _get(LocalBmpStringValueBlock.prototype.__proto__ || Object.getPrototypeOf(LocalBmpStringValueBlock.prototype), 'toJSON', this).call(this);
				} catch (ex) {}


				object.value = this.value;

				return object;
			}
		}], [{
			key: 'blockName',
			value: function blockName() {
				return "BmpStringValueBlock";
			}
		}]);

		return LocalBmpStringValueBlock;
	}(LocalHexBlock(LocalBaseBlock));

	var BmpString = function (_BaseBlock11) {
		_inherits(BmpString, _BaseBlock11);

		function BmpString() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, BmpString);

			var _this33 = _possibleConstructorReturn(this, (BmpString.__proto__ || Object.getPrototypeOf(BmpString)).call(this, parameters, LocalBmpStringValueBlock));

			if ("value" in parameters) _this33.fromString(parameters.value);

			_this33.idBlock.tagClass = 1;
			_this33.idBlock.tagNumber = 30;return _this33;
		}

		_createClass(BmpString, [{
			key: 'fromBER',
			value: function fromBER(inputBuffer, inputOffset, inputLength) {
				var resultOffset = this.valueBlock.fromBER(inputBuffer, inputOffset, this.lenBlock.isIndefiniteForm === true ? inputLength : this.lenBlock.length);
				if (resultOffset === -1) {
					this.error = this.valueBlock.error;
					return resultOffset;
				}

				this.fromBuffer(this.valueBlock.valueHex);

				if (this.idBlock.error.length === 0) this.blockLength += this.idBlock.blockLength;

				if (this.lenBlock.error.length === 0) this.blockLength += this.lenBlock.blockLength;

				if (this.valueBlock.error.length === 0) this.blockLength += this.valueBlock.blockLength;

				return resultOffset;
			}
		}, {
			key: 'fromBuffer',
			value: function fromBuffer(inputBuffer) {
				var copyBuffer = inputBuffer.slice(0);
				var valueView = new Uint8Array(copyBuffer);

				for (var i = 0; i < valueView.length; i = i + 2) {
					var temp = valueView[i];

					valueView[i] = valueView[i + 1];
					valueView[i + 1] = temp;
				}

				this.valueBlock.value = String.fromCharCode.apply(null, new Uint16Array(copyBuffer));
			}
		}, {
			key: 'fromString',
			value: function fromString(inputString) {
				var strLength = inputString.length;

				this.valueBlock.valueHex = new ArrayBuffer(strLength * 2);
				var valueHexView = new Uint8Array(this.valueBlock.valueHex);

				for (var i = 0; i < strLength; i++) {
					var codeBuf = utilToBase(inputString.charCodeAt(i), 8);
					var codeView = new Uint8Array(codeBuf);
					if (codeView.length > 2) continue;

					var dif = 2 - codeView.length;

					for (var j = codeView.length - 1; j >= 0; j--) {
						valueHexView[i * 2 + j + dif] = codeView[j];
					}
				}

				this.valueBlock.value = inputString;
			}
		}], [{
			key: 'blockName',
			value: function blockName() {
				return "BmpString";
			}
		}]);

		return BmpString;
	}(BaseBlock);

	var LocalUniversalStringValueBlock = function (_LocalHexBlock8) {
		_inherits(LocalUniversalStringValueBlock, _LocalHexBlock8);

		function LocalUniversalStringValueBlock() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, LocalUniversalStringValueBlock);

			var _this34 = _possibleConstructorReturn(this, (LocalUniversalStringValueBlock.__proto__ || Object.getPrototypeOf(LocalUniversalStringValueBlock)).call(this, parameters));

			_this34.isHexOnly = true;
			_this34.value = "";
			return _this34;
		}

		_createClass(LocalUniversalStringValueBlock, [{
			key: 'toJSON',
			value: function toJSON() {
				var object = {};

				try {
					object = _get(LocalUniversalStringValueBlock.prototype.__proto__ || Object.getPrototypeOf(LocalUniversalStringValueBlock.prototype), 'toJSON', this).call(this);
				} catch (ex) {}


				object.value = this.value;

				return object;
			}
		}], [{
			key: 'blockName',
			value: function blockName() {
				return "UniversalStringValueBlock";
			}
		}]);

		return LocalUniversalStringValueBlock;
	}(LocalHexBlock(LocalBaseBlock));

	var UniversalString = function (_BaseBlock12) {
		_inherits(UniversalString, _BaseBlock12);

		function UniversalString() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, UniversalString);

			var _this35 = _possibleConstructorReturn(this, (UniversalString.__proto__ || Object.getPrototypeOf(UniversalString)).call(this, parameters, LocalUniversalStringValueBlock));

			if ("value" in parameters) _this35.fromString(parameters.value);

			_this35.idBlock.tagClass = 1;
			_this35.idBlock.tagNumber = 28;return _this35;
		}

		_createClass(UniversalString, [{
			key: 'fromBER',
			value: function fromBER(inputBuffer, inputOffset, inputLength) {
				var resultOffset = this.valueBlock.fromBER(inputBuffer, inputOffset, this.lenBlock.isIndefiniteForm === true ? inputLength : this.lenBlock.length);
				if (resultOffset === -1) {
					this.error = this.valueBlock.error;
					return resultOffset;
				}

				this.fromBuffer(this.valueBlock.valueHex);

				if (this.idBlock.error.length === 0) this.blockLength += this.idBlock.blockLength;

				if (this.lenBlock.error.length === 0) this.blockLength += this.lenBlock.blockLength;

				if (this.valueBlock.error.length === 0) this.blockLength += this.valueBlock.blockLength;

				return resultOffset;
			}
		}, {
			key: 'fromBuffer',
			value: function fromBuffer(inputBuffer) {
				var copyBuffer = inputBuffer.slice(0);
				var valueView = new Uint8Array(copyBuffer);

				for (var i = 0; i < valueView.length; i = i + 4) {
					valueView[i] = valueView[i + 3];
					valueView[i + 1] = valueView[i + 2];
					valueView[i + 2] = 0x00;
					valueView[i + 3] = 0x00;
				}

				this.valueBlock.value = String.fromCharCode.apply(null, new Uint32Array(copyBuffer));
			}
		}, {
			key: 'fromString',
			value: function fromString(inputString) {
				var strLength = inputString.length;

				this.valueBlock.valueHex = new ArrayBuffer(strLength * 4);
				var valueHexView = new Uint8Array(this.valueBlock.valueHex);

				for (var i = 0; i < strLength; i++) {
					var codeBuf = utilToBase(inputString.charCodeAt(i), 8);
					var codeView = new Uint8Array(codeBuf);
					if (codeView.length > 4) continue;

					var dif = 4 - codeView.length;

					for (var j = codeView.length - 1; j >= 0; j--) {
						valueHexView[i * 4 + j + dif] = codeView[j];
					}
				}

				this.valueBlock.value = inputString;
			}
		}], [{
			key: 'blockName',
			value: function blockName() {
				return "UniversalString";
			}
		}]);

		return UniversalString;
	}(BaseBlock);

	var LocalSimpleStringValueBlock = function (_LocalHexBlock9) {
		_inherits(LocalSimpleStringValueBlock, _LocalHexBlock9);

		function LocalSimpleStringValueBlock() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, LocalSimpleStringValueBlock);

			var _this36 = _possibleConstructorReturn(this, (LocalSimpleStringValueBlock.__proto__ || Object.getPrototypeOf(LocalSimpleStringValueBlock)).call(this, parameters));

			_this36.value = "";
			_this36.isHexOnly = true;
			return _this36;
		}

		_createClass(LocalSimpleStringValueBlock, [{
			key: 'toJSON',
			value: function toJSON() {
				var object = {};

				try {
					object = _get(LocalSimpleStringValueBlock.prototype.__proto__ || Object.getPrototypeOf(LocalSimpleStringValueBlock.prototype), 'toJSON', this).call(this);
				} catch (ex) {}


				object.value = this.value;

				return object;
			}
		}], [{
			key: 'blockName',
			value: function blockName() {
				return "SimpleStringValueBlock";
			}
		}]);

		return LocalSimpleStringValueBlock;
	}(LocalHexBlock(LocalBaseBlock));

	var LocalSimpleStringBlock = function (_BaseBlock13) {
		_inherits(LocalSimpleStringBlock, _BaseBlock13);

		function LocalSimpleStringBlock() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, LocalSimpleStringBlock);

			var _this37 = _possibleConstructorReturn(this, (LocalSimpleStringBlock.__proto__ || Object.getPrototypeOf(LocalSimpleStringBlock)).call(this, parameters, LocalSimpleStringValueBlock));

			if ("value" in parameters) _this37.fromString(parameters.value);
			return _this37;
		}

		_createClass(LocalSimpleStringBlock, [{
			key: 'fromBER',
			value: function fromBER(inputBuffer, inputOffset, inputLength) {
				var resultOffset = this.valueBlock.fromBER(inputBuffer, inputOffset, this.lenBlock.isIndefiniteForm === true ? inputLength : this.lenBlock.length);
				if (resultOffset === -1) {
					this.error = this.valueBlock.error;
					return resultOffset;
				}

				this.fromBuffer(this.valueBlock.valueHex);

				if (this.idBlock.error.length === 0) this.blockLength += this.idBlock.blockLength;

				if (this.lenBlock.error.length === 0) this.blockLength += this.lenBlock.blockLength;

				if (this.valueBlock.error.length === 0) this.blockLength += this.valueBlock.blockLength;

				return resultOffset;
			}
		}, {
			key: 'fromBuffer',
			value: function fromBuffer(inputBuffer) {
				this.valueBlock.value = String.fromCharCode.apply(null, new Uint8Array(inputBuffer));
			}
		}, {
			key: 'fromString',
			value: function fromString(inputString) {
				var strLen = inputString.length;

				this.valueBlock.valueHex = new ArrayBuffer(strLen);
				var view = new Uint8Array(this.valueBlock.valueHex);

				for (var i = 0; i < strLen; i++) {
					view[i] = inputString.charCodeAt(i);
				}this.valueBlock.value = inputString;
			}
		}], [{
			key: 'blockName',
			value: function blockName() {
				return "SIMPLESTRING";
			}
		}]);

		return LocalSimpleStringBlock;
	}(BaseBlock);

	var NumericString = function (_LocalSimpleStringBlo) {
		_inherits(NumericString, _LocalSimpleStringBlo);

		function NumericString() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, NumericString);

			var _this38 = _possibleConstructorReturn(this, (NumericString.__proto__ || Object.getPrototypeOf(NumericString)).call(this, parameters));

			_this38.idBlock.tagClass = 1;
			_this38.idBlock.tagNumber = 18;return _this38;
		}

		_createClass(NumericString, null, [{
			key: 'blockName',
			value: function blockName() {
				return "NumericString";
			}
		}]);

		return NumericString;
	}(LocalSimpleStringBlock);

	var PrintableString = function (_LocalSimpleStringBlo2) {
		_inherits(PrintableString, _LocalSimpleStringBlo2);

		function PrintableString() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, PrintableString);

			var _this39 = _possibleConstructorReturn(this, (PrintableString.__proto__ || Object.getPrototypeOf(PrintableString)).call(this, parameters));

			_this39.idBlock.tagClass = 1;
			_this39.idBlock.tagNumber = 19;return _this39;
		}

		_createClass(PrintableString, null, [{
			key: 'blockName',
			value: function blockName() {
				return "PrintableString";
			}
		}]);

		return PrintableString;
	}(LocalSimpleStringBlock);

	var TeletexString = function (_LocalSimpleStringBlo3) {
		_inherits(TeletexString, _LocalSimpleStringBlo3);

		function TeletexString() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, TeletexString);

			var _this40 = _possibleConstructorReturn(this, (TeletexString.__proto__ || Object.getPrototypeOf(TeletexString)).call(this, parameters));

			_this40.idBlock.tagClass = 1;
			_this40.idBlock.tagNumber = 20;return _this40;
		}

		_createClass(TeletexString, null, [{
			key: 'blockName',
			value: function blockName() {
				return "TeletexString";
			}
		}]);

		return TeletexString;
	}(LocalSimpleStringBlock);

	var VideotexString = function (_LocalSimpleStringBlo4) {
		_inherits(VideotexString, _LocalSimpleStringBlo4);

		function VideotexString() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, VideotexString);

			var _this41 = _possibleConstructorReturn(this, (VideotexString.__proto__ || Object.getPrototypeOf(VideotexString)).call(this, parameters));

			_this41.idBlock.tagClass = 1;
			_this41.idBlock.tagNumber = 21;return _this41;
		}

		_createClass(VideotexString, null, [{
			key: 'blockName',
			value: function blockName() {
				return "VideotexString";
			}
		}]);

		return VideotexString;
	}(LocalSimpleStringBlock);

	var IA5String = function (_LocalSimpleStringBlo5) {
		_inherits(IA5String, _LocalSimpleStringBlo5);

		function IA5String() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, IA5String);

			var _this42 = _possibleConstructorReturn(this, (IA5String.__proto__ || Object.getPrototypeOf(IA5String)).call(this, parameters));

			_this42.idBlock.tagClass = 1;
			_this42.idBlock.tagNumber = 22;return _this42;
		}

		_createClass(IA5String, null, [{
			key: 'blockName',
			value: function blockName() {
				return "IA5String";
			}
		}]);

		return IA5String;
	}(LocalSimpleStringBlock);

	var GraphicString = function (_LocalSimpleStringBlo6) {
		_inherits(GraphicString, _LocalSimpleStringBlo6);

		function GraphicString() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, GraphicString);

			var _this43 = _possibleConstructorReturn(this, (GraphicString.__proto__ || Object.getPrototypeOf(GraphicString)).call(this, parameters));

			_this43.idBlock.tagClass = 1;
			_this43.idBlock.tagNumber = 25;return _this43;
		}

		_createClass(GraphicString, null, [{
			key: 'blockName',
			value: function blockName() {
				return "GraphicString";
			}
		}]);

		return GraphicString;
	}(LocalSimpleStringBlock);

	var VisibleString = function (_LocalSimpleStringBlo7) {
		_inherits(VisibleString, _LocalSimpleStringBlo7);

		function VisibleString() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, VisibleString);

			var _this44 = _possibleConstructorReturn(this, (VisibleString.__proto__ || Object.getPrototypeOf(VisibleString)).call(this, parameters));

			_this44.idBlock.tagClass = 1;
			_this44.idBlock.tagNumber = 26;return _this44;
		}

		_createClass(VisibleString, null, [{
			key: 'blockName',
			value: function blockName() {
				return "VisibleString";
			}
		}]);

		return VisibleString;
	}(LocalSimpleStringBlock);

	var GeneralString = function (_LocalSimpleStringBlo8) {
		_inherits(GeneralString, _LocalSimpleStringBlo8);

		function GeneralString() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, GeneralString);

			var _this45 = _possibleConstructorReturn(this, (GeneralString.__proto__ || Object.getPrototypeOf(GeneralString)).call(this, parameters));

			_this45.idBlock.tagClass = 1;
			_this45.idBlock.tagNumber = 27;return _this45;
		}

		_createClass(GeneralString, null, [{
			key: 'blockName',
			value: function blockName() {
				return "GeneralString";
			}
		}]);

		return GeneralString;
	}(LocalSimpleStringBlock);

	var CharacterString = function (_LocalSimpleStringBlo9) {
		_inherits(CharacterString, _LocalSimpleStringBlo9);

		function CharacterString() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, CharacterString);

			var _this46 = _possibleConstructorReturn(this, (CharacterString.__proto__ || Object.getPrototypeOf(CharacterString)).call(this, parameters));

			_this46.idBlock.tagClass = 1;
			_this46.idBlock.tagNumber = 29;return _this46;
		}

		_createClass(CharacterString, null, [{
			key: 'blockName',
			value: function blockName() {
				return "CharacterString";
			}
		}]);

		return CharacterString;
	}(LocalSimpleStringBlock);

	var UTCTime = function (_VisibleString) {
		_inherits(UTCTime, _VisibleString);

		function UTCTime() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, UTCTime);

			var _this47 = _possibleConstructorReturn(this, (UTCTime.__proto__ || Object.getPrototypeOf(UTCTime)).call(this, parameters));

			_this47.year = 0;
			_this47.month = 0;
			_this47.day = 0;
			_this47.hour = 0;
			_this47.minute = 0;
			_this47.second = 0;

			if ("value" in parameters) {
				_this47.fromString(parameters.value);

				_this47.valueBlock.valueHex = new ArrayBuffer(parameters.value.length);
				var view = new Uint8Array(_this47.valueBlock.valueHex);

				for (var i = 0; i < parameters.value.length; i++) {
					view[i] = parameters.value.charCodeAt(i);
				}
			}

			if ("valueDate" in parameters) {
				_this47.fromDate(parameters.valueDate);
				_this47.valueBlock.valueHex = _this47.toBuffer();
			}


			_this47.idBlock.tagClass = 1;
			_this47.idBlock.tagNumber = 23;return _this47;
		}

		_createClass(UTCTime, [{
			key: 'fromBER',
			value: function fromBER(inputBuffer, inputOffset, inputLength) {
				var resultOffset = this.valueBlock.fromBER(inputBuffer, inputOffset, this.lenBlock.isIndefiniteForm === true ? inputLength : this.lenBlock.length);
				if (resultOffset === -1) {
					this.error = this.valueBlock.error;
					return resultOffset;
				}

				this.fromBuffer(this.valueBlock.valueHex);

				if (this.idBlock.error.length === 0) this.blockLength += this.idBlock.blockLength;

				if (this.lenBlock.error.length === 0) this.blockLength += this.lenBlock.blockLength;

				if (this.valueBlock.error.length === 0) this.blockLength += this.valueBlock.blockLength;

				return resultOffset;
			}
		}, {
			key: 'fromBuffer',
			value: function fromBuffer(inputBuffer) {
				this.fromString(String.fromCharCode.apply(null, new Uint8Array(inputBuffer)));
			}
		}, {
			key: 'toBuffer',
			value: function toBuffer() {
				var str = this.toString();

				var buffer = new ArrayBuffer(str.length);
				var view = new Uint8Array(buffer);

				for (var i = 0; i < str.length; i++) {
					view[i] = str.charCodeAt(i);
				}return buffer;
			}
		}, {
			key: 'fromDate',
			value: function fromDate(inputDate) {
				this.year = inputDate.getUTCFullYear();
				this.month = inputDate.getUTCMonth() + 1;
				this.day = inputDate.getUTCDate();
				this.hour = inputDate.getUTCHours();
				this.minute = inputDate.getUTCMinutes();
				this.second = inputDate.getUTCSeconds();
			}
		}, {
			key: 'toDate',
			value: function toDate() {
				return new Date(Date.UTC(this.year, this.month - 1, this.day, this.hour, this.minute, this.second));
			}
		}, {
			key: 'fromString',
			value: function fromString(inputString) {
				var parser = /(\d{2})(\d{2})(\d{2})(\d{2})(\d{2})(\d{2})Z/ig;
				var parserArray = parser.exec(inputString);
				if (parserArray === null) {
					this.error = "Wrong input string for convertion";
					return;
				}

				var year = parseInt(parserArray[1], 10);
				if (year >= 50) this.year = 1900 + year;else this.year = 2000 + year;

				this.month = parseInt(parserArray[2], 10);
				this.day = parseInt(parserArray[3], 10);
				this.hour = parseInt(parserArray[4], 10);
				this.minute = parseInt(parserArray[5], 10);
				this.second = parseInt(parserArray[6], 10);
			}
		}, {
			key: 'toString',
			value: function toString() {
				var outputArray = new Array(7);

				outputArray[0] = padNumber(this.year < 2000 ? this.year - 1900 : this.year - 2000, 2);
				outputArray[1] = padNumber(this.month, 2);
				outputArray[2] = padNumber(this.day, 2);
				outputArray[3] = padNumber(this.hour, 2);
				outputArray[4] = padNumber(this.minute, 2);
				outputArray[5] = padNumber(this.second, 2);
				outputArray[6] = "Z";

				return outputArray.join("");
			}
		}, {
			key: 'toJSON',
			value: function toJSON() {
				var object = {};

				try {
					object = _get(UTCTime.prototype.__proto__ || Object.getPrototypeOf(UTCTime.prototype), 'toJSON', this).call(this);
				} catch (ex) {}


				object.year = this.year;
				object.month = this.month;
				object.day = this.day;
				object.hour = this.hour;
				object.minute = this.minute;
				object.second = this.second;

				return object;
			}
		}], [{
			key: 'blockName',
			value: function blockName() {
				return "UTCTime";
			}
		}]);

		return UTCTime;
	}(VisibleString);

	var GeneralizedTime = function (_VisibleString2) {
		_inherits(GeneralizedTime, _VisibleString2);

		function GeneralizedTime() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, GeneralizedTime);

			var _this48 = _possibleConstructorReturn(this, (GeneralizedTime.__proto__ || Object.getPrototypeOf(GeneralizedTime)).call(this, parameters));

			_this48.year = 0;
			_this48.month = 0;
			_this48.day = 0;
			_this48.hour = 0;
			_this48.minute = 0;
			_this48.second = 0;
			_this48.millisecond = 0;

			if ("value" in parameters) {
				_this48.fromString(parameters.value);

				_this48.valueBlock.valueHex = new ArrayBuffer(parameters.value.length);
				var view = new Uint8Array(_this48.valueBlock.valueHex);

				for (var i = 0; i < parameters.value.length; i++) {
					view[i] = parameters.value.charCodeAt(i);
				}
			}

			if ("valueDate" in parameters) {
				_this48.fromDate(parameters.valueDate);
				_this48.valueBlock.valueHex = _this48.toBuffer();
			}


			_this48.idBlock.tagClass = 1;
			_this48.idBlock.tagNumber = 24;return _this48;
		}

		_createClass(GeneralizedTime, [{
			key: 'fromBER',
			value: function fromBER(inputBuffer, inputOffset, inputLength) {
				var resultOffset = this.valueBlock.fromBER(inputBuffer, inputOffset, this.lenBlock.isIndefiniteForm === true ? inputLength : this.lenBlock.length);
				if (resultOffset === -1) {
					this.error = this.valueBlock.error;
					return resultOffset;
				}

				this.fromBuffer(this.valueBlock.valueHex);

				if (this.idBlock.error.length === 0) this.blockLength += this.idBlock.blockLength;

				if (this.lenBlock.error.length === 0) this.blockLength += this.lenBlock.blockLength;

				if (this.valueBlock.error.length === 0) this.blockLength += this.valueBlock.blockLength;

				return resultOffset;
			}
		}, {
			key: 'fromBuffer',
			value: function fromBuffer(inputBuffer) {
				this.fromString(String.fromCharCode.apply(null, new Uint8Array(inputBuffer)));
			}
		}, {
			key: 'toBuffer',
			value: function toBuffer() {
				var str = this.toString();

				var buffer = new ArrayBuffer(str.length);
				var view = new Uint8Array(buffer);

				for (var i = 0; i < str.length; i++) {
					view[i] = str.charCodeAt(i);
				}return buffer;
			}
		}, {
			key: 'fromDate',
			value: function fromDate(inputDate) {
				this.year = inputDate.getUTCFullYear();
				this.month = inputDate.getUTCMonth() + 1;
				this.day = inputDate.getUTCDate();
				this.hour = inputDate.getUTCHours();
				this.minute = inputDate.getUTCMinutes();
				this.second = inputDate.getUTCSeconds();
				this.millisecond = inputDate.getUTCMilliseconds();
			}
		}, {
			key: 'toDate',
			value: function toDate() {
				return new Date(Date.UTC(this.year, this.month - 1, this.day, this.hour, this.minute, this.second, this.millisecond));
			}
		}, {
			key: 'fromString',
			value: function fromString(inputString) {
				var isUTC = false;

				var timeString = "";
				var dateTimeString = "";
				var fractionPart = 0;

				var parser = void 0;

				var hourDifference = 0;
				var minuteDifference = 0;

				if (inputString[inputString.length - 1] === "Z") {
					timeString = inputString.substr(0, inputString.length - 1);

					isUTC = true;
				} else {
						var number = new Number(inputString[inputString.length - 1]);

						if (isNaN(number.valueOf())) throw new Error("Wrong input string for convertion");

						timeString = inputString;
					}

				if (isUTC) {
					if (timeString.indexOf("+") !== -1) throw new Error("Wrong input string for convertion");

					if (timeString.indexOf("-") !== -1) throw new Error("Wrong input string for convertion");
				} else {
						var multiplier = 1;
						var differencePosition = timeString.indexOf("+");
						var differenceString = "";

						if (differencePosition === -1) {
							differencePosition = timeString.indexOf("-");
							multiplier = -1;
						}

						if (differencePosition !== -1) {
							differenceString = timeString.substr(differencePosition + 1);
							timeString = timeString.substr(0, differencePosition);

							if (differenceString.length !== 2 && differenceString.length !== 4) throw new Error("Wrong input string for convertion");

							var _number = new Number(differenceString.substr(0, 2));

							if (isNaN(_number.valueOf())) throw new Error("Wrong input string for convertion");

							hourDifference = multiplier * _number;

							if (differenceString.length === 4) {
								_number = new Number(differenceString.substr(2, 2));

								if (isNaN(_number.valueOf())) throw new Error("Wrong input string for convertion");

								minuteDifference = multiplier * _number;
							}
						}
					}

				var fractionPointPosition = timeString.indexOf(".");
				if (fractionPointPosition === -1) fractionPointPosition = timeString.indexOf(",");
				if (fractionPointPosition !== -1) {
					var fractionPartCheck = new Number('0' + timeString.substr(fractionPointPosition));

					if (isNaN(fractionPartCheck.valueOf())) throw new Error("Wrong input string for convertion");

					fractionPart = fractionPartCheck.valueOf();

					dateTimeString = timeString.substr(0, fractionPointPosition);
				} else dateTimeString = timeString;

				switch (true) {
					case dateTimeString.length === 8:
						parser = /(\d{4})(\d{2})(\d{2})/ig;
						if (fractionPointPosition !== -1) throw new Error("Wrong input string for convertion");
						break;
					case dateTimeString.length === 10:
						parser = /(\d{4})(\d{2})(\d{2})(\d{2})/ig;

						if (fractionPointPosition !== -1) {
							var fractionResult = 60 * fractionPart;
							this.minute = Math.floor(fractionResult);

							fractionResult = 60 * (fractionResult - this.minute);
							this.second = Math.floor(fractionResult);

							fractionResult = 1000 * (fractionResult - this.second);
							this.millisecond = Math.floor(fractionResult);
						}
						break;
					case dateTimeString.length === 12:
						parser = /(\d{4})(\d{2})(\d{2})(\d{2})(\d{2})/ig;

						if (fractionPointPosition !== -1) {
							var _fractionResult = 60 * fractionPart;
							this.second = Math.floor(_fractionResult);

							_fractionResult = 1000 * (_fractionResult - this.second);
							this.millisecond = Math.floor(_fractionResult);
						}
						break;
					case dateTimeString.length === 14:
						parser = /(\d{4})(\d{2})(\d{2})(\d{2})(\d{2})(\d{2})/ig;

						if (fractionPointPosition !== -1) {
							var _fractionResult2 = 1000 * fractionPart;
							this.millisecond = Math.floor(_fractionResult2);
						}
						break;
					default:
						throw new Error("Wrong input string for convertion");
				}

				var parserArray = parser.exec(dateTimeString);
				if (parserArray === null) throw new Error("Wrong input string for convertion");

				for (var j = 1; j < parserArray.length; j++) {
					switch (j) {
						case 1:
							this.year = parseInt(parserArray[j], 10);
							break;
						case 2:
							this.month = parseInt(parserArray[j], 10);
							break;
						case 3:
							this.day = parseInt(parserArray[j], 10);
							break;
						case 4:
							this.hour = parseInt(parserArray[j], 10) + hourDifference;
							break;
						case 5:
							this.minute = parseInt(parserArray[j], 10) + minuteDifference;
							break;
						case 6:
							this.second = parseInt(parserArray[j], 10);
							break;
						default:
							throw new Error("Wrong input string for convertion");
					}
				}

				if (isUTC === false) {
					var tempDate = new Date(this.year, this.month, this.day, this.hour, this.minute, this.second, this.millisecond);

					this.year = tempDate.getUTCFullYear();
					this.month = tempDate.getUTCMonth();
					this.day = tempDate.getUTCDay();
					this.hour = tempDate.getUTCHours();
					this.minute = tempDate.getUTCMinutes();
					this.second = tempDate.getUTCSeconds();
					this.millisecond = tempDate.getUTCMilliseconds();
				}
			}
		}, {
			key: 'toString',
			value: function toString() {
				var outputArray = [];

				outputArray.push(padNumber(this.year, 4));
				outputArray.push(padNumber(this.month, 2));
				outputArray.push(padNumber(this.day, 2));
				outputArray.push(padNumber(this.hour, 2));
				outputArray.push(padNumber(this.minute, 2));
				outputArray.push(padNumber(this.second, 2));
				if (this.millisecond !== 0) {
					outputArray.push(".");
					outputArray.push(padNumber(this.millisecond, 3));
				}
				outputArray.push("Z");

				return outputArray.join("");
			}
		}, {
			key: 'toJSON',
			value: function toJSON() {
				var object = {};

				try {
					object = _get(GeneralizedTime.prototype.__proto__ || Object.getPrototypeOf(GeneralizedTime.prototype), 'toJSON', this).call(this);
				} catch (ex) {}


				object.year = this.year;
				object.month = this.month;
				object.day = this.day;
				object.hour = this.hour;
				object.minute = this.minute;
				object.second = this.second;
				object.millisecond = this.millisecond;

				return object;
			}
		}], [{
			key: 'blockName',
			value: function blockName() {
				return "GeneralizedTime";
			}
		}]);

		return GeneralizedTime;
	}(VisibleString);

	var DATE = function (_Utf8String) {
		_inherits(DATE, _Utf8String);

		function DATE() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, DATE);

			var _this49 = _possibleConstructorReturn(this, (DATE.__proto__ || Object.getPrototypeOf(DATE)).call(this, parameters));

			_this49.idBlock.tagClass = 1;
			_this49.idBlock.tagNumber = 31;return _this49;
		}

		_createClass(DATE, null, [{
			key: 'blockName',
			value: function blockName() {
				return "DATE";
			}
		}]);

		return DATE;
	}(Utf8String);

	var TimeOfDay = function (_Utf8String2) {
		_inherits(TimeOfDay, _Utf8String2);

		function TimeOfDay() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, TimeOfDay);

			var _this50 = _possibleConstructorReturn(this, (TimeOfDay.__proto__ || Object.getPrototypeOf(TimeOfDay)).call(this, parameters));

			_this50.idBlock.tagClass = 1;
			_this50.idBlock.tagNumber = 32;return _this50;
		}

		_createClass(TimeOfDay, null, [{
			key: 'blockName',
			value: function blockName() {
				return "TimeOfDay";
			}
		}]);

		return TimeOfDay;
	}(Utf8String);

	var DateTime = function (_Utf8String3) {
		_inherits(DateTime, _Utf8String3);

		function DateTime() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, DateTime);

			var _this51 = _possibleConstructorReturn(this, (DateTime.__proto__ || Object.getPrototypeOf(DateTime)).call(this, parameters));

			_this51.idBlock.tagClass = 1;
			_this51.idBlock.tagNumber = 33;return _this51;
		}

		_createClass(DateTime, null, [{
			key: 'blockName',
			value: function blockName() {
				return "DateTime";
			}
		}]);

		return DateTime;
	}(Utf8String);

	var Duration = function (_Utf8String4) {
		_inherits(Duration, _Utf8String4);

		function Duration() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, Duration);

			var _this52 = _possibleConstructorReturn(this, (Duration.__proto__ || Object.getPrototypeOf(Duration)).call(this, parameters));

			_this52.idBlock.tagClass = 1;
			_this52.idBlock.tagNumber = 34;return _this52;
		}

		_createClass(Duration, null, [{
			key: 'blockName',
			value: function blockName() {
				return "Duration";
			}
		}]);

		return Duration;
	}(Utf8String);

	var TIME = function (_Utf8String5) {
		_inherits(TIME, _Utf8String5);

		function TIME() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, TIME);

			var _this53 = _possibleConstructorReturn(this, (TIME.__proto__ || Object.getPrototypeOf(TIME)).call(this, parameters));

			_this53.idBlock.tagClass = 1;
			_this53.idBlock.tagNumber = 14;return _this53;
		}

		_createClass(TIME, null, [{
			key: 'blockName',
			value: function blockName() {
				return "TIME";
			}
		}]);

		return TIME;
	}(Utf8String);

	var Choice = function Choice() {
		var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

		_classCallCheck(this, Choice);

		this.value = getParametersValue(parameters, "value", []);
		this.optional = getParametersValue(parameters, "optional", false);
	};

	var Any$1 = function Any$1() {
		var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

		_classCallCheck(this, Any$1);

		this.name = getParametersValue(parameters, "name", "");
		this.optional = getParametersValue(parameters, "optional", false);
	};

	var Repeated = function Repeated() {
		var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

		_classCallCheck(this, Repeated);

		this.name = getParametersValue(parameters, "name", "");
		this.optional = getParametersValue(parameters, "optional", false);
		this.value = getParametersValue(parameters, "value", new Any$1());
		this.local = getParametersValue(parameters, "local", false);
	};

	var RawData = function () {
		function RawData() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, RawData);

			this.data = getParametersValue(parameters, "data", new ArrayBuffer(0));
		}

		_createClass(RawData, [{
			key: 'fromBER',
			value: function fromBER(inputBuffer, inputOffset, inputLength) {
				this.data = inputBuffer.slice(inputOffset, inputLength);
			}
		}, {
			key: 'toBER',
			value: function toBER() {
				var sizeOnly = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : false;

				return this.data;
			}
		}]);

		return RawData;
	}();

	function LocalFromBER(inputBuffer, inputOffset, inputLength) {
		var incomingOffset = inputOffset;
		function localChangeType(inputObject, newType) {
			if (inputObject instanceof newType) return inputObject;

			var newObject = new newType();
			newObject.idBlock = inputObject.idBlock;
			newObject.lenBlock = inputObject.lenBlock;
			newObject.warnings = inputObject.warnings;

			newObject.valueBeforeDecode = inputObject.valueBeforeDecode.slice(0);

			return newObject;
		}

		var returnObject = new BaseBlock({}, Object);

		if (checkBufferParams(new LocalBaseBlock(), inputBuffer, inputOffset, inputLength) === false) {
			returnObject.error = "Wrong input parameters";
			return {
				offset: -1,
				result: returnObject
			};
		}

		var intBuffer = new Uint8Array(inputBuffer, inputOffset, inputLength);

		if (intBuffer.length === 0) {
			this.error = "Zero buffer length";
			return {
				offset: -1,
				result: returnObject
			};
		}

		var resultOffset = returnObject.idBlock.fromBER(inputBuffer, inputOffset, inputLength);
		returnObject.warnings.concat(returnObject.idBlock.warnings);
		if (resultOffset === -1) {
			returnObject.error = returnObject.idBlock.error;
			return {
				offset: -1,
				result: returnObject
			};
		}

		inputOffset = resultOffset;
		inputLength -= returnObject.idBlock.blockLength;

		resultOffset = returnObject.lenBlock.fromBER(inputBuffer, inputOffset, inputLength);
		returnObject.warnings.concat(returnObject.lenBlock.warnings);
		if (resultOffset === -1) {
			returnObject.error = returnObject.lenBlock.error;
			return {
				offset: -1,
				result: returnObject
			};
		}

		inputOffset = resultOffset;
		inputLength -= returnObject.lenBlock.blockLength;

		if (returnObject.idBlock.isConstructed === false && returnObject.lenBlock.isIndefiniteForm === true) {
			returnObject.error = "Indefinite length form used for primitive encoding form";
			return {
				offset: -1,
				result: returnObject
			};
		}

		var newASN1Type = BaseBlock;

		switch (returnObject.idBlock.tagClass) {
			case 1:
				if (returnObject.idBlock.tagNumber >= 37 && returnObject.idBlock.isHexOnly === false) {
					returnObject.error = "UNIVERSAL 37 and upper tags are reserved by ASN.1 standard";
					return {
						offset: -1,
						result: returnObject
					};
				}


				switch (returnObject.idBlock.tagNumber) {
					case 0:
						if (returnObject.idBlock.isConstructed === true && returnObject.lenBlock.length > 0) {
							returnObject.error = "Type [UNIVERSAL 0] is reserved";
							return {
								offset: -1,
								result: returnObject
							};
						}


						newASN1Type = EndOfContent;

						break;

					case 1:
						newASN1Type = Boolean;
						break;

					case 2:
						newASN1Type = Integer;
						break;

					case 3:
						newASN1Type = BitString;
						break;

					case 4:
						newASN1Type = OctetString;
						break;

					case 5:
						newASN1Type = Null;
						break;

					case 6:
						newASN1Type = ObjectIdentifier$1;
						break;

					case 10:
						newASN1Type = Enumerated;
						break;

					case 12:
						newASN1Type = Utf8String;
						break;

					case 14:
						newASN1Type = TIME;
						break;

					case 15:
						returnObject.error = "[UNIVERSAL 15] is reserved by ASN.1 standard";
						return {
							offset: -1,
							result: returnObject
						};

					case 16:
						newASN1Type = Sequence;
						break;

					case 17:
						newASN1Type = Set;
						break;

					case 18:
						newASN1Type = NumericString;
						break;

					case 19:
						newASN1Type = PrintableString;
						break;

					case 20:
						newASN1Type = TeletexString;
						break;

					case 21:
						newASN1Type = VideotexString;
						break;

					case 22:
						newASN1Type = IA5String;
						break;

					case 23:
						newASN1Type = UTCTime;
						break;

					case 24:
						newASN1Type = GeneralizedTime;
						break;

					case 25:
						newASN1Type = GraphicString;
						break;

					case 26:
						newASN1Type = VisibleString;
						break;

					case 27:
						newASN1Type = GeneralString;
						break;

					case 28:
						newASN1Type = UniversalString;
						break;

					case 29:
						newASN1Type = CharacterString;
						break;

					case 30:
						newASN1Type = BmpString;
						break;

					case 31:
						newASN1Type = DATE;
						break;

					case 32:
						newASN1Type = TimeOfDay;
						break;

					case 33:
						newASN1Type = DateTime;
						break;

					case 34:
						newASN1Type = Duration;
						break;

					default:
						{
							var newObject = void 0;

							if (returnObject.idBlock.isConstructed === true) newObject = new Constructed();else newObject = new Primitive();

							newObject.idBlock = returnObject.idBlock;
							newObject.lenBlock = returnObject.lenBlock;
							newObject.warnings = returnObject.warnings;

							returnObject = newObject;

							resultOffset = returnObject.fromBER(inputBuffer, inputOffset, inputLength);
						}
				}
				break;

			case 2:
			case 3:
			case 4:
			default:
				{
					if (returnObject.idBlock.isConstructed === true) newASN1Type = Constructed;else newASN1Type = Primitive;
				}
		}

		returnObject = localChangeType(returnObject, newASN1Type);
		resultOffset = returnObject.fromBER(inputBuffer, inputOffset, returnObject.lenBlock.isIndefiniteForm === true ? inputLength : returnObject.lenBlock.length);

		returnObject.valueBeforeDecode = inputBuffer.slice(incomingOffset, incomingOffset + returnObject.blockLength);


		return {
			offset: resultOffset,
			result: returnObject
		};
	}

	function fromBER(inputBuffer) {
		if (inputBuffer.byteLength === 0) {
			var result = new BaseBlock({}, Object);
			result.error = "Input buffer has zero length";

			return {
				offset: -1,
				result: result
			};
		}

		return LocalFromBER(inputBuffer, 0, inputBuffer.byteLength);
	}

	function compareSchema(root, inputData, inputSchema) {
		if (inputSchema instanceof Choice) {
			var choiceResult = false;

			for (var j = 0; j < inputSchema.value.length; j++) {
				var result = compareSchema(root, inputData, inputSchema.value[j]);
				if (result.verified === true) {
					return {
						verified: true,
						result: root
					};
				}
			}

			if (choiceResult === false) {
				var _result = {
					verified: false,
					result: {
						error: "Wrong values for Choice type"
					}
				};

				if (inputSchema.hasOwnProperty("name")) _result.name = inputSchema.name;

				return _result;
			}
		}

		if (inputSchema instanceof Any$1) {
			if (inputSchema.hasOwnProperty("name")) root[inputSchema.name] = inputData;


			return {
				verified: true,
				result: root
			};
		}

		if (root instanceof Object === false) {
			return {
				verified: false,
				result: { error: "Wrong root object" }
			};
		}

		if (inputData instanceof Object === false) {
			return {
				verified: false,
				result: { error: "Wrong ASN.1 data" }
			};
		}

		if (inputSchema instanceof Object === false) {
			return {
				verified: false,
				result: { error: "Wrong ASN.1 schema" }
			};
		}

		if ("idBlock" in inputSchema === false) {
			return {
				verified: false,
				result: { error: "Wrong ASN.1 schema" }
			};
		}

		if ("fromBER" in inputSchema.idBlock === false) {
			return {
				verified: false,
				result: { error: "Wrong ASN.1 schema" }
			};
		}

		if ("toBER" in inputSchema.idBlock === false) {
			return {
				verified: false,
				result: { error: "Wrong ASN.1 schema" }
			};
		}

		var encodedId = inputSchema.idBlock.toBER(false);
		if (encodedId.byteLength === 0) {
			return {
				verified: false,
				result: { error: "Error encoding idBlock for ASN.1 schema" }
			};
		}

		var decodedOffset = inputSchema.idBlock.fromBER(encodedId, 0, encodedId.byteLength);
		if (decodedOffset === -1) {
			return {
				verified: false,
				result: { error: "Error decoding idBlock for ASN.1 schema" }
			};
		}

		if (inputSchema.idBlock.hasOwnProperty("tagClass") === false) {
			return {
				verified: false,
				result: { error: "Wrong ASN.1 schema" }
			};
		}

		if (inputSchema.idBlock.tagClass !== inputData.idBlock.tagClass) {
			return {
				verified: false,
				result: root
			};
		}

		if (inputSchema.idBlock.hasOwnProperty("tagNumber") === false) {
			return {
				verified: false,
				result: { error: "Wrong ASN.1 schema" }
			};
		}

		if (inputSchema.idBlock.tagNumber !== inputData.idBlock.tagNumber) {
			return {
				verified: false,
				result: root
			};
		}

		if (inputSchema.idBlock.hasOwnProperty("isConstructed") === false) {
			return {
				verified: false,
				result: { error: "Wrong ASN.1 schema" }
			};
		}

		if (inputSchema.idBlock.isConstructed !== inputData.idBlock.isConstructed) {
			return {
				verified: false,
				result: root
			};
		}

		if ("isHexOnly" in inputSchema.idBlock === false) {
				return {
					verified: false,
					result: { error: "Wrong ASN.1 schema" }
				};
			}

		if (inputSchema.idBlock.isHexOnly !== inputData.idBlock.isHexOnly) {
			return {
				verified: false,
				result: root
			};
		}

		if (inputSchema.idBlock.isHexOnly === true) {
			if ("valueHex" in inputSchema.idBlock === false) {
					return {
						verified: false,
						result: { error: "Wrong ASN.1 schema" }
					};
				}

			var schemaView = new Uint8Array(inputSchema.idBlock.valueHex);
			var asn1View = new Uint8Array(inputData.idBlock.valueHex);

			if (schemaView.length !== asn1View.length) {
				return {
					verified: false,
					result: root
				};
			}

			for (var i = 0; i < schemaView.length; i++) {
				if (schemaView[i] !== asn1View[1]) {
					return {
						verified: false,
						result: root
					};
				}
			}
		}

		if (inputSchema.hasOwnProperty("name")) {
			inputSchema.name = inputSchema.name.replace(/^\s+|\s+$/g, "");
			if (inputSchema.name !== "") root[inputSchema.name] = inputData;
		}

		if (inputSchema.idBlock.isConstructed === true) {
			var admission = 0;
			var _result2 = { verified: false };

			var maxLength = inputSchema.valueBlock.value.length;

			if (maxLength > 0) {
				if (inputSchema.valueBlock.value[0] instanceof Repeated) maxLength = inputData.valueBlock.value.length;
			}

			if (maxLength === 0) {
				return {
					verified: true,
					result: root
				};
			}

			if (inputData.valueBlock.value.length === 0 && inputSchema.valueBlock.value.length !== 0) {
				var _optional = true;

				for (var _i8 = 0; _i8 < inputSchema.valueBlock.value.length; _i8++) {
					_optional = _optional && (inputSchema.valueBlock.value[_i8].optional || false);
				}if (_optional === true) {
					return {
						verified: true,
						result: root
					};
				}

				if (inputSchema.hasOwnProperty("name")) {
					inputSchema.name = inputSchema.name.replace(/^\s+|\s+$/g, "");
					if (inputSchema.name !== "") delete root[inputSchema.name];
				}


				root.error = "Inconsistent object length";

				return {
					verified: false,
					result: root
				};
			}


			for (var _i9 = 0; _i9 < maxLength; _i9++) {
				if (_i9 - admission >= inputData.valueBlock.value.length) {
					if (inputSchema.valueBlock.value[_i9].optional === false) {
						var _result3 = {
							verified: false,
							result: root
						};

						root.error = "Inconsistent length between ASN.1 data and schema";

						if (inputSchema.hasOwnProperty("name")) {
							inputSchema.name = inputSchema.name.replace(/^\s+|\s+$/g, "");
							if (inputSchema.name !== "") {
								delete root[inputSchema.name];
								_result3.name = inputSchema.name;
							}
						}


						return _result3;
					}
				} else {
						if (inputSchema.valueBlock.value[0] instanceof Repeated) {
							_result2 = compareSchema(root, inputData.valueBlock.value[_i9], inputSchema.valueBlock.value[0].value);
							if (_result2.verified === false) {
								if (inputSchema.valueBlock.value[0].optional === true) admission++;else {
									if (inputSchema.hasOwnProperty("name")) {
										inputSchema.name = inputSchema.name.replace(/^\s+|\s+$/g, "");
										if (inputSchema.name !== "") delete root[inputSchema.name];
									}


									return _result2;
								}
							}

							if ("name" in inputSchema.valueBlock.value[0] && inputSchema.valueBlock.value[0].name.length > 0) {
								var arrayRoot = {};

								if ("local" in inputSchema.valueBlock.value[0] && inputSchema.valueBlock.value[0].local === true) arrayRoot = inputData;else arrayRoot = root;

								if (typeof arrayRoot[inputSchema.valueBlock.value[0].name] === "undefined") arrayRoot[inputSchema.valueBlock.value[0].name] = [];

								arrayRoot[inputSchema.valueBlock.value[0].name].push(inputData.valueBlock.value[_i9]);
							}
						} else {
								_result2 = compareSchema(root, inputData.valueBlock.value[_i9 - admission], inputSchema.valueBlock.value[_i9]);
								if (_result2.verified === false) {
									if (inputSchema.valueBlock.value[_i9].optional === true) admission++;else {
										if (inputSchema.hasOwnProperty("name")) {
											inputSchema.name = inputSchema.name.replace(/^\s+|\s+$/g, "");
											if (inputSchema.name !== "") delete root[inputSchema.name];
										}


										return _result2;
									}
								}
							}
					}
			}

			if (_result2.verified === false) {
					var _result4 = {
						verified: false,
						result: root
					};

					if (inputSchema.hasOwnProperty("name")) {
						inputSchema.name = inputSchema.name.replace(/^\s+|\s+$/g, "");
						if (inputSchema.name !== "") {
							delete root[inputSchema.name];
							_result4.name = inputSchema.name;
						}
					}


					return _result4;
				}

			return {
				verified: true,
				result: root
			};
		}

		if ("primitiveSchema" in inputSchema && "valueHex" in inputData.valueBlock) {
			var asn1 = fromBER(inputData.valueBlock.valueHex);
			if (asn1.offset === -1) {
				var _result5 = {
					verified: false,
					result: asn1.result
				};

				if (inputSchema.hasOwnProperty("name")) {
					inputSchema.name = inputSchema.name.replace(/^\s+|\s+$/g, "");
					if (inputSchema.name !== "") {
						delete root[inputSchema.name];
						_result5.name = inputSchema.name;
					}
				}


				return _result5;
			}


			return compareSchema(root, asn1.result, inputSchema.primitiveSchema);
		}

		return {
			verified: true,
			result: root
		};
	}

	function getParametersValue$1(parameters, name, defaultValue) {
		if (parameters instanceof Object === false) return defaultValue;

		if (name in parameters) return parameters[name];

		return defaultValue;
	}

	function bufferToHexCodes$1(inputBuffer) {
		var inputOffset = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : 0;
		var inputLength = arguments.length > 2 && arguments[2] !== undefined ? arguments[2] : inputBuffer.byteLength;

		var result = "";

		var _iteratorNormalCompletion7 = true;
		var _didIteratorError7 = false;
		var _iteratorError7 = undefined;

		try {
			for (var _iterator7 = new Uint8Array(inputBuffer, inputOffset, inputLength)[Symbol.iterator](), _step7; !(_iteratorNormalCompletion7 = (_step7 = _iterator7.next()).done); _iteratorNormalCompletion7 = true) {
				var item = _step7.value;

				var str = item.toString(16).toUpperCase();
				result = result + (str.length === 1 ? "0" : "") + str;
			}
		} catch (err) {
			_didIteratorError7 = true;
			_iteratorError7 = err;
		} finally {
			try {
				if (!_iteratorNormalCompletion7 && _iterator7.return) {
					_iterator7.return();
				}
			} finally {
				if (_didIteratorError7) {
					throw _iteratorError7;
				}
			}
		}

		return result;
	}

	function utilConcatBuf$1() {
		var outputLength = 0;
		var prevLength = 0;

		for (var _len7 = arguments.length, buffers = Array(_len7), _key7 = 0; _key7 < _len7; _key7++) {
			buffers[_key7] = arguments[_key7];
		}

		var _iteratorNormalCompletion8 = true;
		var _didIteratorError8 = false;
		var _iteratorError8 = undefined;

		try {

			for (var _iterator8 = buffers[Symbol.iterator](), _step8; !(_iteratorNormalCompletion8 = (_step8 = _iterator8.next()).done); _iteratorNormalCompletion8 = true) {
				var buffer = _step8.value;

				outputLength += buffer.byteLength;
			}
		} catch (err) {
			_didIteratorError8 = true;
			_iteratorError8 = err;
		} finally {
			try {
				if (!_iteratorNormalCompletion8 && _iterator8.return) {
					_iterator8.return();
				}
			} finally {
				if (_didIteratorError8) {
					throw _iteratorError8;
				}
			}
		}

		var retBuf = new ArrayBuffer(outputLength);
		var retView = new Uint8Array(retBuf);

		var _iteratorNormalCompletion9 = true;
		var _didIteratorError9 = false;
		var _iteratorError9 = undefined;

		try {
			for (var _iterator9 = buffers[Symbol.iterator](), _step9; !(_iteratorNormalCompletion9 = (_step9 = _iterator9.next()).done); _iteratorNormalCompletion9 = true) {
				var _buffer2 = _step9.value;

				retView.set(new Uint8Array(_buffer2), prevLength);
				prevLength += _buffer2.byteLength;
			}
		} catch (err) {
			_didIteratorError9 = true;
			_iteratorError9 = err;
		} finally {
			try {
				if (!_iteratorNormalCompletion9 && _iterator9.return) {
					_iterator9.return();
				}
			} finally {
				if (_didIteratorError9) {
					throw _iteratorError9;
				}
			}
		}

		return retBuf;
	}

	function isEqualBuffer$1(inputBuffer1, inputBuffer2) {
		if (inputBuffer1.byteLength !== inputBuffer2.byteLength) return false;

		var view1 = new Uint8Array(inputBuffer1);
		var view2 = new Uint8Array(inputBuffer2);

		for (var i = 0; i < view1.length; i++) {
			if (view1[i] !== view2[i]) return false;
		}

		return true;
	}

	var base64Template$1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
	var base64UrlTemplate$1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_=";

	function toBase64$1(input) {
		var useUrlTemplate = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : false;
		var skipPadding = arguments.length > 2 && arguments[2] !== undefined ? arguments[2] : false;
		var skipLeadingZeros = arguments.length > 3 && arguments[3] !== undefined ? arguments[3] : false;

		var i = 0;

		var flag1 = 0;
		var flag2 = 0;

		var output = "";

		var template = useUrlTemplate ? base64UrlTemplate$1 : base64Template$1;

		if (skipLeadingZeros) {
			var nonZeroPosition = 0;

			for (var _i10 = 0; _i10 < input.length; _i10++) {
				if (input.charCodeAt(_i10) !== 0) {
					nonZeroPosition = _i10;
					break;
				}
			}

			input = input.slice(nonZeroPosition);
		}

		while (i < input.length) {
			var chr1 = input.charCodeAt(i++);
			if (i >= input.length) flag1 = 1;
			var chr2 = input.charCodeAt(i++);
			if (i >= input.length) flag2 = 1;
			var chr3 = input.charCodeAt(i++);

			var enc1 = chr1 >> 2;
			var enc2 = (chr1 & 0x03) << 4 | chr2 >> 4;
			var enc3 = (chr2 & 0x0F) << 2 | chr3 >> 6;
			var enc4 = chr3 & 0x3F;

			if (flag1 === 1) enc3 = enc4 = 64;else {
				if (flag2 === 1) enc4 = 64;
			}

			if (skipPadding) {
				if (enc3 === 64) output += '' + template.charAt(enc1) + template.charAt(enc2);else {
					if (enc4 === 64) output += '' + template.charAt(enc1) + template.charAt(enc2) + template.charAt(enc3);else output += '' + template.charAt(enc1) + template.charAt(enc2) + template.charAt(enc3) + template.charAt(enc4);
				}
			} else output += '' + template.charAt(enc1) + template.charAt(enc2) + template.charAt(enc3) + template.charAt(enc4);
		}

		return output;
	}

	function fromBase64$1(input) {
		var useUrlTemplate = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : false;
		var cutTailZeros = arguments.length > 2 && arguments[2] !== undefined ? arguments[2] : false;

		var template = useUrlTemplate ? base64UrlTemplate$1 : base64Template$1;

		function indexof(toSearch) {
			for (var _i11 = 0; _i11 < 64; _i11++) {
				if (template.charAt(_i11) === toSearch) return _i11;
			}

			return 64;
		}

		function test(incoming) {
			return incoming === 64 ? 0x00 : incoming;
		}


		var i = 0;

		var output = "";

		while (i < input.length) {
			var enc1 = indexof(input.charAt(i++));
			var enc2 = i >= input.length ? 0x00 : indexof(input.charAt(i++));
			var enc3 = i >= input.length ? 0x00 : indexof(input.charAt(i++));
			var enc4 = i >= input.length ? 0x00 : indexof(input.charAt(i++));

			var chr1 = test(enc1) << 2 | test(enc2) >> 4;
			var chr2 = (test(enc2) & 0x0F) << 4 | test(enc3) >> 2;
			var chr3 = (test(enc3) & 0x03) << 6 | test(enc4);

			output += String.fromCharCode(chr1);

			if (enc3 !== 64) output += String.fromCharCode(chr2);

			if (enc4 !== 64) output += String.fromCharCode(chr3);
		}

		if (cutTailZeros) {
			var outputLength = output.length;
			var nonZeroStart = -1;

			for (var _i12 = outputLength - 1; _i12 >= 0; _i12--) {
				if (output.charCodeAt(_i12) !== 0) {
					nonZeroStart = _i12;
					break;
				}
			}

			if (nonZeroStart !== -1) output = output.slice(0, nonZeroStart + 1);
		}

		return output;
	}

	function arrayBufferToString$1(buffer) {
		var resultString = "";
		var view = new Uint8Array(buffer);

		var _iteratorNormalCompletion10 = true;
		var _didIteratorError10 = false;
		var _iteratorError10 = undefined;

		try {
			for (var _iterator10 = view[Symbol.iterator](), _step10; !(_iteratorNormalCompletion10 = (_step10 = _iterator10.next()).done); _iteratorNormalCompletion10 = true) {
				var element = _step10.value;

				resultString = resultString + String.fromCharCode(element);
			}
		} catch (err) {
			_didIteratorError10 = true;
			_iteratorError10 = err;
		} finally {
			try {
				if (!_iteratorNormalCompletion10 && _iterator10.return) {
					_iterator10.return();
				}
			} finally {
				if (_didIteratorError10) {
					throw _iteratorError10;
				}
			}
		}

		return resultString;
	}

	function stringToArrayBuffer$1(str) {
		var stringLength = str.length;

		var resultBuffer = new ArrayBuffer(stringLength);
		var resultView = new Uint8Array(resultBuffer);

		for (var i = 0; i < stringLength; i++) {
			resultView[i] = str.charCodeAt(i);
		}return resultBuffer;
	}

	var log2$1 = Math.log(2);

	function nearestPowerOf2$1(length) {
		var base = Math.log(length) / log2$1;

		var floor = Math.floor(base);
		var round = Math.round(base);

		return floor === round ? floor : round;
	}

	var AlgorithmIdentifier = function () {
		function AlgorithmIdentifier() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, AlgorithmIdentifier);

			this.algorithmId = getParametersValue$1(parameters, "algorithmId", AlgorithmIdentifier.defaultValues("algorithmId"));

			if ("algorithmParams" in parameters) this.algorithmParams = getParametersValue$1(parameters, "algorithmParams", AlgorithmIdentifier.defaultValues("algorithmParams"));

			if ("schema" in parameters) this.fromSchema(parameters.schema);
		}

		_createClass(AlgorithmIdentifier, [{
			key: 'fromSchema',
			value: function fromSchema(schema) {
				var asn1 = compareSchema(schema, schema, AlgorithmIdentifier.schema({
					names: {
						algorithmIdentifier: "algorithm",
						algorithmParams: "params"
					}
				}));

				if (asn1.verified === false) throw new Error("Object's schema was not verified against input data for AlgorithmIdentifier");

				this.algorithmId = asn1.result.algorithm.valueBlock.toString();
				if ("params" in asn1.result) this.algorithmParams = asn1.result.params;
			}
		}, {
			key: 'toSchema',
			value: function toSchema() {
				var outputArray = [];

				outputArray.push(new ObjectIdentifier$1({ value: this.algorithmId }));
				if ("algorithmParams" in this && this.algorithmParams instanceof Any$1 === false) outputArray.push(this.algorithmParams);

				return new Sequence({
					value: outputArray
				});
			}
		}, {
			key: 'toJSON',
			value: function toJSON() {
				var object = {
					algorithmId: this.algorithmId
				};

				if ("algorithmParams" in this && this.algorithmParams instanceof Any$1 === false) object.algorithmParams = this.algorithmParams.toJSON();

				return object;
			}
		}, {
			key: 'isEqual',
			value: function isEqual(algorithmIdentifier) {
				if (algorithmIdentifier instanceof AlgorithmIdentifier === false) return false;

				if (this.algorithmId !== algorithmIdentifier.algorithmId) return false;

				if ("algorithmParams" in this) {
					if ("algorithmParams" in algorithmIdentifier) return JSON.stringify(this.algorithmParams) === JSON.stringify(algorithmIdentifier.algorithmParams);

					return false;
				}

				if ("algorithmParams" in algorithmIdentifier) return false;


				return true;
			}
		}], [{
			key: 'defaultValues',
			value: function defaultValues(memberName) {
				switch (memberName) {
					case "algorithmId":
						return "";
					case "algorithmParams":
						return new Any$1();
					default:
						throw new Error('Invalid member name for AlgorithmIdentifier class: ' + memberName);
				}
			}
		}, {
			key: 'compareWithDefault',
			value: function compareWithDefault(memberName, memberValue) {
				switch (memberName) {
					case "algorithmId":
						return memberValue === "";
					case "algorithmParams":
						return memberValue instanceof Any$1;
					default:
						throw new Error('Invalid member name for AlgorithmIdentifier class: ' + memberName);
				}
			}
		}, {
			key: 'schema',
			value: function schema() {
				var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

				var names = getParametersValue$1(parameters, "names", {});

				return new Sequence({
					name: names.blockName || "",
					optional: names.optional || false,
					value: [new ObjectIdentifier$1({ name: names.algorithmIdentifier || "" }), new Any$1({ name: names.algorithmParams || "", optional: true })]
				});
			}
		}]);

		return AlgorithmIdentifier;
	}();

	var ECPublicKey = function () {
		function ECPublicKey() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, ECPublicKey);

			this.x = getParametersValue$1(parameters, "x", ECPublicKey.defaultValues("x"));

			this.y = getParametersValue$1(parameters, "y", ECPublicKey.defaultValues("y"));

			this.namedCurve = getParametersValue$1(parameters, "namedCurve", ECPublicKey.defaultValues("namedCurve"));

			if ("schema" in parameters) this.fromSchema(parameters.schema);

			if ("json" in parameters) this.fromJSON(parameters.json);
		}

		_createClass(ECPublicKey, [{
			key: 'fromSchema',
			value: function fromSchema(schema) {
				if (schema instanceof ArrayBuffer === false) throw new Error("Object's schema was not verified against input data for ECPublicKey");

				var view = new Uint8Array(schema);
				if (view[0] !== 0x04) throw new Error("Object's schema was not verified against input data for ECPublicKey");

				var coordinateLength = void 0;

				switch (this.namedCurve) {
					case "1.2.840.10045.3.1.7":
						coordinateLength = 32;
						break;
					case "1.3.132.0.34":
						coordinateLength = 48;
						break;
					case "1.3.132.0.35":
						coordinateLength = 66;
						break;
					default:
						throw new Error('Incorrect curve OID: ' + this.namedCurve);
				}

				if (schema.byteLength !== coordinateLength * 2 + 1) throw new Error("Object's schema was not verified against input data for ECPublicKey");

				this.x = schema.slice(1, coordinateLength + 1);
				this.y = schema.slice(1 + coordinateLength, coordinateLength * 2 + 1);
			}
		}, {
			key: 'toSchema',
			value: function toSchema() {
				return new RawData({ data: utilConcatBuf$1(new Uint8Array([0x04]).buffer, this.x, this.y)
				});
			}
		}, {
			key: 'toJSON',
			value: function toJSON() {
				var crvName = "";

				switch (this.namedCurve) {
					case "1.2.840.10045.3.1.7":
						crvName = "P-256";
						break;
					case "1.3.132.0.34":
						crvName = "P-384";
						break;
					case "1.3.132.0.35":
						crvName = "P-521";
						break;
					default:
				}

				return {
					crv: crvName,
					x: toBase64$1(arrayBufferToString$1(this.x), true, true, false),
					y: toBase64$1(arrayBufferToString$1(this.y), true, true, false)
				};
			}
		}, {
			key: 'fromJSON',
			value: function fromJSON(json) {
				var coodinateLength = 0;

				if ("crv" in json) {
					switch (json.crv.toUpperCase()) {
						case "P-256":
							this.namedCurve = "1.2.840.10045.3.1.7";
							coodinateLength = 32;
							break;
						case "P-384":
							this.namedCurve = "1.3.132.0.34";
							coodinateLength = 48;
							break;
						case "P-521":
							this.namedCurve = "1.3.132.0.35";
							coodinateLength = 66;
							break;
						default:
					}
				} else throw new Error("Absent mandatory parameter \"crv\"");

				if ("x" in json) {
					var convertBuffer = stringToArrayBuffer$1(fromBase64$1(json.x, true));

					if (convertBuffer.byteLength < coodinateLength) {
						this.x = new ArrayBuffer(coodinateLength);
						var view = new Uint8Array(this.x);
						var convertBufferView = new Uint8Array(convertBuffer);
						view.set(1, convertBufferView);
					} else this.x = convertBuffer.slice(0, coodinateLength);
				} else throw new Error("Absent mandatory parameter \"x\"");

				if ("y" in json) {
					var _convertBuffer = stringToArrayBuffer$1(fromBase64$1(json.y, true));

					if (_convertBuffer.byteLength < coodinateLength) {
						this.y = new ArrayBuffer(coodinateLength);
						var _view3 = new Uint8Array(this.y);
						var _convertBufferView = new Uint8Array(_convertBuffer);
						_view3.set(1, _convertBufferView);
					} else this.y = _convertBuffer.slice(0, coodinateLength);
				} else throw new Error("Absent mandatory parameter \"y\"");
			}
		}], [{
			key: 'defaultValues',
			value: function defaultValues(memberName) {
				switch (memberName) {
					case "x":
					case "y":
						return new ArrayBuffer(0);
					case "namedCurve":
						return "";
					default:
						throw new Error('Invalid member name for ECCPublicKey class: ' + memberName);
				}
			}
		}, {
			key: 'compareWithDefault',
			value: function compareWithDefault(memberName, memberValue) {
				switch (memberName) {
					case "x":
					case "y":
						return isEqualBuffer$1(memberValue, ECPublicKey.defaultValues(memberName));
					case "namedCurve":
						return memberValue === "";
					default:
						throw new Error('Invalid member name for ECCPublicKey class: ' + memberName);
				}
			}
		}, {
			key: 'schema',
			value: function schema() {
				var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

				return new RawData();
			}
		}]);

		return ECPublicKey;
	}();

	var RSAPublicKey = function () {
		function RSAPublicKey() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, RSAPublicKey);

			this.modulus = getParametersValue$1(parameters, "modulus", RSAPublicKey.defaultValues("modulus"));

			this.publicExponent = getParametersValue$1(parameters, "publicExponent", RSAPublicKey.defaultValues("publicExponent"));

			if ("schema" in parameters) this.fromSchema(parameters.schema);

			if ("json" in parameters) this.fromJSON(parameters.json);
		}

		_createClass(RSAPublicKey, [{
			key: 'fromSchema',
			value: function fromSchema(schema) {
				var asn1 = compareSchema(schema, schema, RSAPublicKey.schema({
					names: {
						modulus: "modulus",
						publicExponent: "publicExponent"
					}
				}));

				if (asn1.verified === false) throw new Error("Object's schema was not verified against input data for RSAPublicKey");

				this.modulus = asn1.result.modulus.convertFromDER(256);
				this.publicExponent = asn1.result.publicExponent;
			}
		}, {
			key: 'toSchema',
			value: function toSchema() {
				return new Sequence({
					value: [this.modulus.convertToDER(), this.publicExponent]
				});
			}
		}, {
			key: 'toJSON',
			value: function toJSON() {
				return {
					n: toBase64$1(arrayBufferToString$1(this.modulus.valueBlock.valueHex), true, true, true),
					e: toBase64$1(arrayBufferToString$1(this.publicExponent.valueBlock.valueHex), true, true, true)
				};
			}
		}, {
			key: 'fromJSON',
			value: function fromJSON(json) {
				if ("n" in json) {
					var array = stringToArrayBuffer$1(fromBase64$1(json.n, true));
					this.modulus = new Integer({ valueHex: array.slice(0, Math.pow(2, nearestPowerOf2$1(array.byteLength))) });
				} else throw new Error("Absent mandatory parameter \"n\"");

				if ("e" in json) this.publicExponent = new Integer({ valueHex: stringToArrayBuffer$1(fromBase64$1(json.e, true)).slice(0, 3) });else throw new Error("Absent mandatory parameter \"e\"");
			}
		}], [{
			key: 'defaultValues',
			value: function defaultValues(memberName) {
				switch (memberName) {
					case "modulus":
						return new Integer();
					case "publicExponent":
						return new Integer();
					default:
						throw new Error('Invalid member name for RSAPublicKey class: ' + memberName);
				}
			}
		}, {
			key: 'schema',
			value: function schema() {
				var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

				var names = getParametersValue$1(parameters, "names", {});

				return new Sequence({
					name: names.blockName || "",
					value: [new Integer({ name: names.modulus || "" }), new Integer({ name: names.publicExponent || "" })]
				});
			}
		}]);

		return RSAPublicKey;
	}();

	var PublicKeyInfo = function () {
		function PublicKeyInfo() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, PublicKeyInfo);

			this.algorithm = getParametersValue$1(parameters, "algorithm", PublicKeyInfo.defaultValues("algorithm"));

			this.subjectPublicKey = getParametersValue$1(parameters, "subjectPublicKey", PublicKeyInfo.defaultValues("subjectPublicKey"));

			if ("parsedKey" in parameters) this.parsedKey = getParametersValue$1(parameters, "parsedKey", PublicKeyInfo.defaultValues("parsedKey"));

			if ("schema" in parameters) this.fromSchema(parameters.schema);

			if ("json" in parameters) this.fromJSON(parameters.json);
		}

		_createClass(PublicKeyInfo, [{
			key: 'fromSchema',
			value: function fromSchema(schema) {
				var asn1 = compareSchema(schema, schema, PublicKeyInfo.schema({
					names: {
						algorithm: {
							names: {
								blockName: "algorithm"
							}
						},
						subjectPublicKey: "subjectPublicKey"
					}
				}));

				if (asn1.verified === false) throw new Error("Object's schema was not verified against input data for PUBLIC_KEY_INFO");

				this.algorithm = new AlgorithmIdentifier({ schema: asn1.result.algorithm });
				this.subjectPublicKey = asn1.result.subjectPublicKey;

				switch (this.algorithm.algorithmId) {
					case "1.2.840.10045.2.1":
						if ("algorithmParams" in this.algorithm) {
							if (this.algorithm.algorithmParams instanceof ObjectIdentifier$1) {
								try {
									this.parsedKey = new ECPublicKey({
										namedCurve: this.algorithm.algorithmParams.valueBlock.toString(),
										schema: this.subjectPublicKey.valueBlock.valueHex
									});
								} catch (ex) {}
							}
						}
						break;
					case "1.2.840.113549.1.1.1":
						{
							var publicKeyASN1 = fromBER(this.subjectPublicKey.valueBlock.valueHex);
							if (publicKeyASN1.offset !== -1) {
								try {
									this.parsedKey = new RSAPublicKey({ schema: publicKeyASN1.result });
								} catch (ex) {}
							}
						}
						break;
					default:
				}
			}
		}, {
			key: 'toSchema',
			value: function toSchema() {
				return new Sequence({
					value: [this.algorithm.toSchema(), this.subjectPublicKey]
				});
			}
		}, {
			key: 'toJSON',
			value: function toJSON() {
				if ("parsedKey" in this === false) {
					return {
						algorithm: this.algorithm.toJSON(),
						subjectPublicKey: this.subjectPublicKey.toJSON()
					};
				}

				var jwk = {};

				switch (this.algorithm.algorithmId) {
					case "1.2.840.10045.2.1":
						jwk.kty = "EC";
						break;
					case "1.2.840.113549.1.1.1":
						jwk.kty = "RSA";
						break;
					default:
				}

				var publicKeyJWK = this.parsedKey.toJSON();

				var _iteratorNormalCompletion11 = true;
				var _didIteratorError11 = false;
				var _iteratorError11 = undefined;

				try {
					for (var _iterator11 = Object.keys(publicKeyJWK)[Symbol.iterator](), _step11; !(_iteratorNormalCompletion11 = (_step11 = _iterator11.next()).done); _iteratorNormalCompletion11 = true) {
						var key = _step11.value;

						jwk[key] = publicKeyJWK[key];
					}
				} catch (err) {
					_didIteratorError11 = true;
					_iteratorError11 = err;
				} finally {
					try {
						if (!_iteratorNormalCompletion11 && _iterator11.return) {
							_iterator11.return();
						}
					} finally {
						if (_didIteratorError11) {
							throw _iteratorError11;
						}
					}
				}

				return jwk;
			}
		}, {
			key: 'fromJSON',
			value: function fromJSON(json) {
				if ("kty" in json) {
					switch (json.kty.toUpperCase()) {
						case "EC":
							this.parsedKey = new ECPublicKey({ json: json });

							this.algorithm = new AlgorithmIdentifier({
								algorithmId: "1.2.840.10045.2.1",
								algorithmParams: new ObjectIdentifier$1({ value: this.parsedKey.namedCurve })
							});
							break;
						case "RSA":
							this.parsedKey = new RSAPublicKey({ json: json });

							this.algorithm = new AlgorithmIdentifier({
								algorithmId: "1.2.840.113549.1.1.1",
								algorithmParams: new Null()
							});
							break;
						default:
							throw new Error('Invalid value for "kty" parameter: ' + json.kty);
					}

					this.subjectPublicKey = new BitString({ valueHex: this.parsedKey.toSchema().toBER(false) });
				}
			}
		}, {
			key: 'importKey',
			value: function importKey(publicKey) {
				var sequence = Promise.resolve();
				var _this = this;

				if (typeof publicKey === "undefined") return Promise.reject("Need to provide publicKey input parameter");

				var crypto = getCrypto();
				if (typeof crypto === "undefined") return Promise.reject("Unable to create WebCrypto object");

				sequence = sequence.then(function () {
					return crypto.exportKey("spki", publicKey);
				});

				sequence = sequence.then(function (exportedKey) {
					var asn1 = fromBER(exportedKey);
					try {
						_this.fromSchema(asn1.result);
					} catch (exception) {
						return Promise.reject("Error during initializing object from schema");
					}

					return undefined;
				}, function (error) {
					return Promise.reject('Error during exporting public key: ' + error);
				});


				return sequence;
			}
		}], [{
			key: 'defaultValues',
			value: function defaultValues(memberName) {
				switch (memberName) {
					case "algorithm":
						return new AlgorithmIdentifier();
					case "subjectPublicKey":
						return new BitString();
					default:
						throw new Error('Invalid member name for PublicKeyInfo class: ' + memberName);
				}
			}
		}, {
			key: 'schema',
			value: function schema() {
				var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

				var names = getParametersValue$1(parameters, "names", {});

				return new Sequence({
					name: names.blockName || "",
					value: [AlgorithmIdentifier.schema(names.algorithm || {}), new BitString({ name: names.subjectPublicKey || "" })]
				});
			}
		}]);

		return PublicKeyInfo;
	}();

	var Attribute = function () {
		function Attribute() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, Attribute);

			this.type = getParametersValue$1(parameters, "type", Attribute.defaultValues("type"));

			this.values = getParametersValue$1(parameters, "values", Attribute.defaultValues("values"));

			if ("schema" in parameters) this.fromSchema(parameters.schema);
		}

		_createClass(Attribute, [{
			key: 'fromSchema',
			value: function fromSchema(schema) {
				var asn1 = compareSchema(schema, schema, Attribute.schema({
					names: {
						type: "type",
						values: "values"
					}
				}));

				if (asn1.verified === false) throw new Error("Object's schema was not verified against input data for ATTRIBUTE");

				this.type = asn1.result.type.valueBlock.toString();
				this.values = asn1.result.values;
			}
		}, {
			key: 'toSchema',
			value: function toSchema() {
				return new Sequence({
					value: [new ObjectIdentifier$1({ value: this.type }), new Set({
						value: this.values
					})]
				});
			}
		}, {
			key: 'toJSON',
			value: function toJSON() {
				return {
					type: this.type,
					values: Array.from(this.values, function (element) {
						return element.toJSON();
					})
				};
			}
		}], [{
			key: 'defaultValues',
			value: function defaultValues(memberName) {
				switch (memberName) {
					case "type":
						return "";
					case "values":
						return [];
					default:
						throw new Error('Invalid member name for Attribute class: ' + memberName);
				}
			}
		}, {
			key: 'compareWithDefault',
			value: function compareWithDefault(memberName, memberValue) {
				switch (memberName) {
					case "type":
						return memberValue === "";
					case "values":
						return memberValue.length === 0;
					default:
						throw new Error('Invalid member name for Attribute class: ' + memberName);
				}
			}
		}, {
			key: 'schema',
			value: function schema() {
				var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

				var names = getParametersValue$1(parameters, "names", {});

				return new Sequence({
					name: names.blockName || "",
					value: [new ObjectIdentifier$1({ name: names.type || "" }), new Set({
						name: names.setName || "",
						value: [new Repeated({
							name: names.values || "",
							value: new Any$1()
						})]
					})]
				});
			}
		}]);

		return Attribute;
	}();

	var ECPrivateKey = function () {
		function ECPrivateKey() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, ECPrivateKey);

			this.version = getParametersValue$1(parameters, "version", ECPrivateKey.defaultValues("version"));

			this.privateKey = getParametersValue$1(parameters, "privateKey", ECPrivateKey.defaultValues("privateKey"));

			if ("namedCurve" in parameters) this.namedCurve = getParametersValue$1(parameters, "namedCurve", ECPrivateKey.defaultValues("namedCurve"));

			if ("publicKey" in parameters) this.publicKey = getParametersValue$1(parameters, "publicKey", ECPrivateKey.defaultValues("publicKey"));

			if ("schema" in parameters) this.fromSchema(parameters.schema);

			if ("json" in parameters) this.fromJSON(parameters.json);
		}

		_createClass(ECPrivateKey, [{
			key: 'fromSchema',
			value: function fromSchema(schema) {
				var asn1 = compareSchema(schema, schema, ECPrivateKey.schema({
					names: {
						version: "version",
						privateKey: "privateKey",
						namedCurve: "namedCurve",
						publicKey: "publicKey"
					}
				}));

				if (asn1.verified === false) throw new Error("Object's schema was not verified against input data for ECPrivateKey");

				this.version = asn1.result.version.valueBlock.valueDec;
				this.privateKey = asn1.result.privateKey;

				if ("namedCurve" in asn1.result) this.namedCurve = asn1.result.namedCurve.valueBlock.toString();

				if ("publicKey" in asn1.result) {
					var publicKeyData = { schema: asn1.result.publicKey.valueBlock.valueHex };
					if ("namedCurve" in this) publicKeyData.namedCurve = this.namedCurve;

					this.publicKey = new ECPublicKey(publicKeyData);
				}
			}
		}, {
			key: 'toSchema',
			value: function toSchema() {
				var outputArray = [new Integer({ value: this.version }), this.privateKey];

				if ("namedCurve" in this) {
					outputArray.push(new Constructed({
						idBlock: {
							tagClass: 3,
							tagNumber: 0 },
						value: [new ObjectIdentifier$1({ value: this.namedCurve })]
					}));
				}

				if ("publicKey" in this) {
					outputArray.push(new Constructed({
						idBlock: {
							tagClass: 3,
							tagNumber: 1 },
						value: [new BitString({ valueHex: this.publicKey.toSchema().toBER(false) })]
					}));
				}

				return new Sequence({
					value: outputArray
				});
			}
		}, {
			key: 'toJSON',
			value: function toJSON() {
				if ("namedCurve" in this === false || ECPrivateKey.compareWithDefault("namedCurve", this.namedCurve)) throw new Error("Not enough information for making JSON: absent \"namedCurve\" value");

				var crvName = "";

				switch (this.namedCurve) {
					case "1.2.840.10045.3.1.7":
						crvName = "P-256";
						break;
					case "1.3.132.0.34":
						crvName = "P-384";
						break;
					case "1.3.132.0.35":
						crvName = "P-521";
						break;
					default:
				}

				var privateKeyJSON = {
					crv: crvName,
					d: toBase64$1(arrayBufferToString$1(this.privateKey.valueBlock.valueHex), true, true, false)
				};

				if ("publicKey" in this) {
					var publicKeyJSON = this.publicKey.toJSON();

					privateKeyJSON.x = publicKeyJSON.x;
					privateKeyJSON.y = publicKeyJSON.y;
				}

				return privateKeyJSON;
			}
		}, {
			key: 'fromJSON',
			value: function fromJSON(json) {
				var coodinateLength = 0;

				if ("crv" in json) {
					switch (json.crv.toUpperCase()) {
						case "P-256":
							this.namedCurve = "1.2.840.10045.3.1.7";
							coodinateLength = 32;
							break;
						case "P-384":
							this.namedCurve = "1.3.132.0.34";
							coodinateLength = 48;
							break;
						case "P-521":
							this.namedCurve = "1.3.132.0.35";
							coodinateLength = 66;
							break;
						default:
					}
				} else throw new Error("Absent mandatory parameter \"crv\"");

				if ("d" in json) {
					var convertBuffer = stringToArrayBuffer$1(fromBase64$1(json.d, true));

					if (convertBuffer.byteLength < coodinateLength) {
						var buffer = new ArrayBuffer(coodinateLength);
						var view = new Uint8Array(buffer);
						var convertBufferView = new Uint8Array(convertBuffer);
						view.set(1, convertBufferView);

						this.privateKey = new OctetString({ valueHex: buffer });
					} else this.privateKey = new OctetString({ valueHex: convertBuffer.slice(0, coodinateLength) });
				} else throw new Error("Absent mandatory parameter \"d\"");

				if ("x" in json && "y" in json) this.publicKey = new ECPublicKey({ json: json });
			}
		}], [{
			key: 'defaultValues',
			value: function defaultValues(memberName) {
				switch (memberName) {
					case "version":
						return 1;
					case "privateKey":
						return new OctetString();
					case "namedCurve":
						return "";
					case "publicKey":
						return new ECPublicKey();
					default:
						throw new Error('Invalid member name for ECCPrivateKey class: ' + memberName);
				}
			}
		}, {
			key: 'compareWithDefault',
			value: function compareWithDefault(memberName, memberValue) {
				switch (memberName) {
					case "version":
						return memberValue === ECPrivateKey.defaultValues(memberName);
					case "privateKey":
						return memberValue.isEqual(ECPrivateKey.defaultValues(memberName));
					case "namedCurve":
						return memberValue === "";
					case "publicKey":
						return ECPublicKey.compareWithDefault("namedCurve", memberValue.namedCurve) && ECPublicKey.compareWithDefault("x", memberValue.x) && ECPublicKey.compareWithDefault("y", memberValue.y);
					default:
						throw new Error('Invalid member name for ECCPrivateKey class: ' + memberName);
				}
			}
		}, {
			key: 'schema',
			value: function schema() {
				var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

				var names = getParametersValue$1(parameters, "names", {});

				return new Sequence({
					name: names.blockName || "",
					value: [new Integer({ name: names.version || "" }), new OctetString({ name: names.privateKey || "" }), new Constructed({
						optional: true,
						idBlock: {
							tagClass: 3,
							tagNumber: 0 },
						value: [new ObjectIdentifier$1({ name: names.namedCurve || "" })]
					}), new Constructed({
						optional: true,
						idBlock: {
							tagClass: 3,
							tagNumber: 1 },
						value: [new BitString({ name: names.publicKey || "" })]
					})]
				});
			}
		}]);

		return ECPrivateKey;
	}();

	var OtherPrimeInfo = function () {
		function OtherPrimeInfo() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, OtherPrimeInfo);

			this.prime = getParametersValue$1(parameters, "prime", OtherPrimeInfo.defaultValues("prime"));

			this.exponent = getParametersValue$1(parameters, "exponent", OtherPrimeInfo.defaultValues("exponent"));

			this.coefficient = getParametersValue$1(parameters, "coefficient", OtherPrimeInfo.defaultValues("coefficient"));

			if ("schema" in parameters) this.fromSchema(parameters.schema);

			if ("json" in parameters) this.fromJSON(parameters.json);
		}

		_createClass(OtherPrimeInfo, [{
			key: 'fromSchema',
			value: function fromSchema(schema) {
				var asn1 = compareSchema(schema, schema, OtherPrimeInfo.schema({
					names: {
						prime: "prime",
						exponent: "exponent",
						coefficient: "coefficient"
					}
				}));

				if (asn1.verified === false) throw new Error("Object's schema was not verified against input data for OtherPrimeInfo");

				this.prime = asn1.result.prime.convertFromDER();
				this.exponent = asn1.result.exponent.convertFromDER();
				this.coefficient = asn1.result.coefficient.convertFromDER();
			}
		}, {
			key: 'toSchema',
			value: function toSchema() {
				return new Sequence({
					value: [this.prime.convertToDER(), this.exponent.convertToDER(), this.coefficient.convertToDER()]
				});
			}
		}, {
			key: 'toJSON',
			value: function toJSON() {
				return {
					r: toBase64$1(arrayBufferToString$1(this.prime.valueBlock.valueHex), true, true),
					d: toBase64$1(arrayBufferToString$1(this.exponent.valueBlock.valueHex), true, true),
					t: toBase64$1(arrayBufferToString$1(this.coefficient.valueBlock.valueHex), true, true)
				};
			}
		}, {
			key: 'fromJSON',
			value: function fromJSON(json) {
				if ("r" in json) this.prime = new Integer({ valueHex: stringToArrayBuffer$1(fromBase64$1(json.r, true)) });else throw new Error("Absent mandatory parameter \"r\"");

				if ("d" in json) this.exponent = new Integer({ valueHex: stringToArrayBuffer$1(fromBase64$1(json.d, true)) });else throw new Error("Absent mandatory parameter \"d\"");

				if ("t" in json) this.coefficient = new Integer({ valueHex: stringToArrayBuffer$1(fromBase64$1(json.t, true)) });else throw new Error("Absent mandatory parameter \"t\"");
			}
		}], [{
			key: 'defaultValues',
			value: function defaultValues(memberName) {
				switch (memberName) {
					case "prime":
						return new Integer();
					case "exponent":
						return new Integer();
					case "coefficient":
						return new Integer();
					default:
						throw new Error('Invalid member name for OtherPrimeInfo class: ' + memberName);
				}
			}
		}, {
			key: 'schema',
			value: function schema() {
				var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

				var names = getParametersValue$1(parameters, "names", {});

				return new Sequence({
					name: names.blockName || "",
					value: [new Integer({ name: names.prime || "" }), new Integer({ name: names.exponent || "" }), new Integer({ name: names.coefficient || "" })]
				});
			}
		}]);

		return OtherPrimeInfo;
	}();

	var RSAPrivateKey = function () {
		function RSAPrivateKey() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, RSAPrivateKey);

			this.version = getParametersValue$1(parameters, "version", RSAPrivateKey.defaultValues("version"));

			this.modulus = getParametersValue$1(parameters, "modulus", RSAPrivateKey.defaultValues("modulus"));

			this.publicExponent = getParametersValue$1(parameters, "publicExponent", RSAPrivateKey.defaultValues("publicExponent"));

			this.privateExponent = getParametersValue$1(parameters, "privateExponent", RSAPrivateKey.defaultValues("privateExponent"));

			this.prime1 = getParametersValue$1(parameters, "prime1", RSAPrivateKey.defaultValues("prime1"));

			this.prime2 = getParametersValue$1(parameters, "prime2", RSAPrivateKey.defaultValues("prime2"));

			this.exponent1 = getParametersValue$1(parameters, "exponent1", RSAPrivateKey.defaultValues("exponent1"));

			this.exponent2 = getParametersValue$1(parameters, "exponent2", RSAPrivateKey.defaultValues("exponent2"));

			this.coefficient = getParametersValue$1(parameters, "coefficient", RSAPrivateKey.defaultValues("coefficient"));

			if ("otherPrimeInfos" in parameters) this.otherPrimeInfos = getParametersValue$1(parameters, "otherPrimeInfos", RSAPrivateKey.defaultValues("otherPrimeInfos"));

			if ("schema" in parameters) this.fromSchema(parameters.schema);

			if ("json" in parameters) this.fromJSON(parameters.json);
		}

		_createClass(RSAPrivateKey, [{
			key: 'fromSchema',
			value: function fromSchema(schema) {
				var asn1 = compareSchema(schema, schema, RSAPrivateKey.schema({
					names: {
						version: "version",
						modulus: "modulus",
						publicExponent: "publicExponent",
						privateExponent: "privateExponent",
						prime1: "prime1",
						prime2: "prime2",
						exponent1: "exponent1",
						exponent2: "exponent2",
						coefficient: "coefficient",
						otherPrimeInfo: {
							names: {
								blockName: "otherPrimeInfos"
							}
						}
					}
				}));

				if (asn1.verified === false) throw new Error("Object's schema was not verified against input data for RSAPrivateKey");

				this.version = asn1.result.version.valueBlock.valueDec;
				this.modulus = asn1.result.modulus.convertFromDER(256);
				this.publicExponent = asn1.result.publicExponent;
				this.privateExponent = asn1.result.privateExponent.convertFromDER(256);
				this.prime1 = asn1.result.prime1.convertFromDER(128);
				this.prime2 = asn1.result.prime2.convertFromDER(128);
				this.exponent1 = asn1.result.exponent1.convertFromDER(128);
				this.exponent2 = asn1.result.exponent2.convertFromDER(128);
				this.coefficient = asn1.result.coefficient.convertFromDER(128);

				if ("otherPrimeInfos" in asn1.result) this.otherPrimeInfos = Array.from(asn1.result.otherPrimeInfos, function (element) {
					return new OtherPrimeInfo({ schema: element });
				});
			}
		}, {
			key: 'toSchema',
			value: function toSchema() {
				var outputArray = [];

				outputArray.push(new Integer({ value: this.version }));
				outputArray.push(this.modulus.convertToDER());
				outputArray.push(this.publicExponent);
				outputArray.push(this.privateExponent.convertToDER());
				outputArray.push(this.prime1.convertToDER());
				outputArray.push(this.prime2.convertToDER());
				outputArray.push(this.exponent1.convertToDER());
				outputArray.push(this.exponent2.convertToDER());
				outputArray.push(this.coefficient.convertToDER());

				if ("otherPrimeInfos" in this) {
					outputArray.push(new Sequence({
						value: Array.from(this.otherPrimeInfos, function (element) {
							return element.toSchema();
						})
					}));
				}

				return new Sequence({
					value: outputArray
				});
			}
		}, {
			key: 'toJSON',
			value: function toJSON() {
				var jwk = {
					n: toBase64$1(arrayBufferToString$1(this.modulus.valueBlock.valueHex), true, true, true),
					e: toBase64$1(arrayBufferToString$1(this.publicExponent.valueBlock.valueHex), true, true, true),
					d: toBase64$1(arrayBufferToString$1(this.privateExponent.valueBlock.valueHex), true, true, true),
					p: toBase64$1(arrayBufferToString$1(this.prime1.valueBlock.valueHex), true, true, true),
					q: toBase64$1(arrayBufferToString$1(this.prime2.valueBlock.valueHex), true, true, true),
					dp: toBase64$1(arrayBufferToString$1(this.exponent1.valueBlock.valueHex), true, true, true),
					dq: toBase64$1(arrayBufferToString$1(this.exponent2.valueBlock.valueHex), true, true, true),
					qi: toBase64$1(arrayBufferToString$1(this.coefficient.valueBlock.valueHex), true, true, true)
				};

				if ("otherPrimeInfos" in this) jwk.oth = Array.from(this.otherPrimeInfos, function (element) {
					return element.toJSON();
				});

				return jwk;
			}
		}, {
			key: 'fromJSON',
			value: function fromJSON(json) {
				if ("n" in json) this.modulus = new Integer({ valueHex: stringToArrayBuffer$1(fromBase64$1(json.n, true, true)) });else throw new Error("Absent mandatory parameter \"n\"");

				if ("e" in json) this.publicExponent = new Integer({ valueHex: stringToArrayBuffer$1(fromBase64$1(json.e, true, true)) });else throw new Error("Absent mandatory parameter \"e\"");

				if ("d" in json) this.privateExponent = new Integer({ valueHex: stringToArrayBuffer$1(fromBase64$1(json.d, true, true)) });else throw new Error("Absent mandatory parameter \"d\"");

				if ("p" in json) this.prime1 = new Integer({ valueHex: stringToArrayBuffer$1(fromBase64$1(json.p, true, true)) });else throw new Error("Absent mandatory parameter \"p\"");

				if ("q" in json) this.prime2 = new Integer({ valueHex: stringToArrayBuffer$1(fromBase64$1(json.q, true, true)) });else throw new Error("Absent mandatory parameter \"q\"");

				if ("dp" in json) this.exponent1 = new Integer({ valueHex: stringToArrayBuffer$1(fromBase64$1(json.dp, true, true)) });else throw new Error("Absent mandatory parameter \"dp\"");

				if ("dq" in json) this.exponent2 = new Integer({ valueHex: stringToArrayBuffer$1(fromBase64$1(json.dq, true, true)) });else throw new Error("Absent mandatory parameter \"dq\"");

				if ("qi" in json) this.coefficient = new Integer({ valueHex: stringToArrayBuffer$1(fromBase64$1(json.qi, true, true)) });else throw new Error("Absent mandatory parameter \"qi\"");

				if ("oth" in json) this.otherPrimeInfos = Array.from(json.oth, function (element) {
					return new OtherPrimeInfo({ json: element });
				});
			}
		}], [{
			key: 'defaultValues',
			value: function defaultValues(memberName) {
				switch (memberName) {
					case "version":
						return 0;
					case "modulus":
						return new Integer();
					case "publicExponent":
						return new Integer();
					case "privateExponent":
						return new Integer();
					case "prime1":
						return new Integer();
					case "prime2":
						return new Integer();
					case "exponent1":
						return new Integer();
					case "exponent2":
						return new Integer();
					case "coefficient":
						return new Integer();
					case "otherPrimeInfos":
						return [];
					default:
						throw new Error('Invalid member name for RSAPrivateKey class: ' + memberName);
				}
			}
		}, {
			key: 'schema',
			value: function schema() {
				var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

				var names = getParametersValue$1(parameters, "names", {});

				return new Sequence({
					name: names.blockName || "",
					value: [new Integer({ name: names.version || "" }), new Integer({ name: names.modulus || "" }), new Integer({ name: names.publicExponent || "" }), new Integer({ name: names.privateExponent || "" }), new Integer({ name: names.prime1 || "" }), new Integer({ name: names.prime2 || "" }), new Integer({ name: names.exponent1 || "" }), new Integer({ name: names.exponent2 || "" }), new Integer({ name: names.coefficient || "" }), new Sequence({
						optional: true,
						value: [new Repeated({
							name: names.otherPrimeInfosName || "",
							value: OtherPrimeInfo.schema(names.otherPrimeInfo || {})
						})]
					})]
				});
			}
		}]);

		return RSAPrivateKey;
	}();

	var PrivateKeyInfo = function () {
		function PrivateKeyInfo() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, PrivateKeyInfo);

			this.version = getParametersValue$1(parameters, "version", PrivateKeyInfo.defaultValues("version"));

			this.privateKeyAlgorithm = getParametersValue$1(parameters, "privateKeyAlgorithm", PrivateKeyInfo.defaultValues("privateKeyAlgorithm"));

			this.privateKey = getParametersValue$1(parameters, "privateKey", PrivateKeyInfo.defaultValues("privateKey"));

			if ("attributes" in parameters) this.attributes = getParametersValue$1(parameters, "attributes", PrivateKeyInfo.defaultValues("attributes"));

			if ("parsedKey" in parameters) this.parsedKey = getParametersValue$1(parameters, "parsedKey", PrivateKeyInfo.defaultValues("parsedKey"));

			if ("schema" in parameters) this.fromSchema(parameters.schema);

			if ("json" in parameters) this.fromJSON(parameters.json);
		}

		_createClass(PrivateKeyInfo, [{
			key: 'fromSchema',
			value: function fromSchema(schema) {
				var asn1 = compareSchema(schema, schema, PrivateKeyInfo.schema({
					names: {
						version: "version",
						privateKeyAlgorithm: {
							names: {
								blockName: "privateKeyAlgorithm"
							}
						},
						privateKey: "privateKey",
						attributes: "attributes"
					}
				}));

				if (asn1.verified === false) throw new Error("Object's schema was not verified against input data for PKCS8");

				this.version = asn1.result.version.valueBlock.valueDec;
				this.privateKeyAlgorithm = new AlgorithmIdentifier({ schema: asn1.result.privateKeyAlgorithm });
				this.privateKey = asn1.result.privateKey;

				if ("attributes" in asn1.result) this.attributes = Array.from(asn1.result.attributes, function (element) {
					return new Attribute({ schema: element });
				});

				switch (this.privateKeyAlgorithm.algorithmId) {
					case "1.2.840.113549.1.1.1":
						{
							var privateKeyASN1 = fromBER(this.privateKey.valueBlock.valueHex);
							if (privateKeyASN1.offset !== -1) this.parsedKey = new RSAPrivateKey({ schema: privateKeyASN1.result });
						}
						break;
					case "1.2.840.10045.2.1":
						if ("algorithmParams" in this.privateKeyAlgorithm) {
							if (this.privateKeyAlgorithm.algorithmParams instanceof ObjectIdentifier$1) {
								var _privateKeyASN = fromBER(this.privateKey.valueBlock.valueHex);
								if (_privateKeyASN.offset !== -1) {
									this.parsedKey = new ECPrivateKey({
										namedCurve: this.privateKeyAlgorithm.algorithmParams.valueBlock.toString(),
										schema: _privateKeyASN.result
									});
								}
							}
						}
						break;
					default:
				}
			}
		}, {
			key: 'toSchema',
			value: function toSchema() {
				var outputArray = [new Integer({ value: this.version }), this.privateKeyAlgorithm.toSchema(), this.privateKey];

				if ("attributes" in this) {
					outputArray.push(new Constructed({
						optional: true,
						idBlock: {
							tagClass: 3,
							tagNumber: 0 },
						value: Array.from(this.attributes, function (element) {
							return element.toSchema();
						})
					}));
				}

				return new Sequence({
					value: outputArray
				});
			}
		}, {
			key: 'toJSON',
			value: function toJSON() {
				if ("parsedKey" in this === false) {
					var object = {
						version: this.version,
						privateKeyAlgorithm: this.privateKeyAlgorithm.toJSON(),
						privateKey: this.privateKey.toJSON()
					};

					if ("attributes" in this) object.attributes = Array.from(this.attributes, function (element) {
						return element.toJSON();
					});

					return object;
				}

				var jwk = {};

				switch (this.privateKeyAlgorithm.algorithmId) {
					case "1.2.840.10045.2.1":
						jwk.kty = "EC";
						break;
					case "1.2.840.113549.1.1.1":
						jwk.kty = "RSA";
						break;
					default:
				}

				var publicKeyJWK = this.parsedKey.toJSON();

				var _iteratorNormalCompletion12 = true;
				var _didIteratorError12 = false;
				var _iteratorError12 = undefined;

				try {
					for (var _iterator12 = Object.keys(publicKeyJWK)[Symbol.iterator](), _step12; !(_iteratorNormalCompletion12 = (_step12 = _iterator12.next()).done); _iteratorNormalCompletion12 = true) {
						var key = _step12.value;

						jwk[key] = publicKeyJWK[key];
					}
				} catch (err) {
					_didIteratorError12 = true;
					_iteratorError12 = err;
				} finally {
					try {
						if (!_iteratorNormalCompletion12 && _iterator12.return) {
							_iterator12.return();
						}
					} finally {
						if (_didIteratorError12) {
							throw _iteratorError12;
						}
					}
				}

				return jwk;
			}
		}, {
			key: 'fromJSON',
			value: function fromJSON(json) {
				if ("kty" in json) {
					switch (json.kty.toUpperCase()) {
						case "EC":
							this.parsedKey = new ECPrivateKey({ json: json });

							this.privateKeyAlgorithm = new AlgorithmIdentifier({
								algorithmId: "1.2.840.10045.2.1",
								algorithmParams: new ObjectIdentifier$1({ value: this.parsedKey.namedCurve })
							});
							break;
						case "RSA":
							this.parsedKey = new RSAPrivateKey({ json: json });

							this.privateKeyAlgorithm = new AlgorithmIdentifier({
								algorithmId: "1.2.840.113549.1.1.1",
								algorithmParams: new Null()
							});
							break;
						default:
							throw new Error('Invalid value for "kty" parameter: ' + json.kty);
					}

					this.privateKey = new OctetString({ valueHex: this.parsedKey.toSchema().toBER(false) });
				}
			}
		}], [{
			key: 'defaultValues',
			value: function defaultValues(memberName) {
				switch (memberName) {
					case "version":
						return 0;
					case "privateKeyAlgorithm":
						return new AlgorithmIdentifier();
					case "privateKey":
						return new OctetString();
					case "attributes":
						return [];
					default:
						throw new Error('Invalid member name for PrivateKeyInfo class: ' + memberName);
				}
			}
		}, {
			key: 'schema',
			value: function schema() {
				var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

				var names = getParametersValue$1(parameters, "names", {});

				return new Sequence({
					name: names.blockName || "",
					value: [new Integer({ name: names.version || "" }), AlgorithmIdentifier.schema(names.privateKeyAlgorithm || {}), new OctetString({ name: names.privateKey || "" }), new Constructed({
						optional: true,
						idBlock: {
							tagClass: 3,
							tagNumber: 0 },
						value: [new Repeated({
							name: names.attributes || "",
							value: Attribute.schema()
						})]
					})]
				});
			}
		}]);

		return PrivateKeyInfo;
	}();

	var EncryptedContentInfo = function () {
		function EncryptedContentInfo() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, EncryptedContentInfo);

			this.contentType = getParametersValue$1(parameters, "contentType", EncryptedContentInfo.defaultValues("contentType"));

			this.contentEncryptionAlgorithm = getParametersValue$1(parameters, "contentEncryptionAlgorithm", EncryptedContentInfo.defaultValues("contentEncryptionAlgorithm"));

			if ("encryptedContent" in parameters) {
				this.encryptedContent = parameters.encryptedContent;

				if (this.encryptedContent.idBlock.tagClass === 1 && this.encryptedContent.idBlock.tagNumber === 4) {
					if (this.encryptedContent.idBlock.isConstructed === false) {
						var constrString = new OctetString({
							idBlock: { isConstructed: true },
							isConstructed: true
						});

						var offset = 0;
						var length = this.encryptedContent.valueBlock.valueHex.byteLength;

						while (length > 0) {
							var pieceView = new Uint8Array(this.encryptedContent.valueBlock.valueHex, offset, offset + 1024 > this.encryptedContent.valueBlock.valueHex.byteLength ? this.encryptedContent.valueBlock.valueHex.byteLength - offset : 1024);
							var _array = new ArrayBuffer(pieceView.length);
							var _view = new Uint8Array(_array);

							for (var i = 0; i < _view.length; i++) {
								_view[i] = pieceView[i];
							}constrString.valueBlock.value.push(new OctetString({ valueHex: _array }));

							length -= pieceView.length;
							offset += pieceView.length;
						}

						this.encryptedContent = constrString;
					}
				}
			}

			if ("schema" in parameters) this.fromSchema(parameters.schema);
		}

		_createClass(EncryptedContentInfo, [{
			key: 'fromSchema',
			value: function fromSchema(schema) {
				var asn1 = compareSchema(schema, schema, EncryptedContentInfo.schema({
					names: {
						contentType: "contentType",
						contentEncryptionAlgorithm: {
							names: {
								blockName: "contentEncryptionAlgorithm"
							}
						},
						encryptedContent: "encryptedContent"
					}
				}));

				if (asn1.verified === false) throw new Error("Object's schema was not verified against input data for EncryptedContentInfo");

				this.contentType = asn1.result.contentType.valueBlock.toString();
				this.contentEncryptionAlgorithm = new AlgorithmIdentifier({ schema: asn1.result.contentEncryptionAlgorithm });

				if ("encryptedContent" in asn1.result) {
					this.encryptedContent = asn1.result.encryptedContent;

					this.encryptedContent.idBlock.tagClass = 1;
					this.encryptedContent.idBlock.tagNumber = 4;
				}
			}
		}, {
			key: 'toSchema',
			value: function toSchema() {
				var sequenceLengthBlock = {
					isIndefiniteForm: false
				};

				var outputArray = [];

				outputArray.push(new ObjectIdentifier$1({ value: this.contentType }));
				outputArray.push(this.contentEncryptionAlgorithm.toSchema());

				if ("encryptedContent" in this) {
					sequenceLengthBlock.isIndefiniteForm = this.encryptedContent.idBlock.isConstructed;

					var encryptedValue = this.encryptedContent;

					encryptedValue.idBlock.tagClass = 3;
					encryptedValue.idBlock.tagNumber = 0;

					encryptedValue.lenBlock.isIndefiniteForm = this.encryptedContent.idBlock.isConstructed;

					outputArray.push(encryptedValue);
				}

				return new Sequence({
					lenBlock: sequenceLengthBlock,
					value: outputArray
				});
			}
		}, {
			key: 'toJSON',
			value: function toJSON() {
				var _object = {
					contentType: this.contentType,
					contentEncryptionAlgorithm: this.contentEncryptionAlgorithm.toJSON()
				};

				if ("encryptedContent" in this) _object.encryptedContent = this.encryptedContent.toJSON();

				return _object;
			}
		}], [{
			key: 'defaultValues',
			value: function defaultValues(memberName) {
				switch (memberName) {
					case "contentType":
						return "";
					case "contentEncryptionAlgorithm":
						return new AlgorithmIdentifier();
					case "encryptedContent":
						return new OctetString();
					default:
						throw new Error('Invalid member name for EncryptedContentInfo class: ' + memberName);
				}
			}
		}, {
			key: 'compareWithDefault',
			value: function compareWithDefault(memberName, memberValue) {
				switch (memberName) {
					case "contentType":
						return memberValue === "";
					case "contentEncryptionAlgorithm":
						return memberValue.algorithmId === "" && "algorithmParams" in memberValue === false;
					case "encryptedContent":
						return memberValue.isEqual(EncryptedContentInfo.defaultValues(memberName));
					default:
						throw new Error('Invalid member name for EncryptedContentInfo class: ' + memberName);
				}
			}
		}, {
			key: 'schema',
			value: function schema() {
				var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

				var names = getParametersValue$1(parameters, "names", {});

				return new Sequence({
					name: names.blockName || "",
					value: [new ObjectIdentifier$1({ name: names.contentType || "" }), AlgorithmIdentifier.schema(names.contentEncryptionAlgorithm || {}), new Choice({
						value: [new Constructed({
							name: names.encryptedContent || "",
							idBlock: {
								tagClass: 3,
								tagNumber: 0 },
							value: [new Repeated({
								value: new OctetString()
							})]
						}), new Primitive({
							name: names.encryptedContent || "",
							idBlock: {
								tagClass: 3,
								tagNumber: 0 }
						})]
					})]
				});
			}
		}]);

		return EncryptedContentInfo;
	}();

	var RSASSAPSSParams = function () {
		function RSASSAPSSParams() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, RSASSAPSSParams);

			this.hashAlgorithm = getParametersValue$1(parameters, "hashAlgorithm", RSASSAPSSParams.defaultValues("hashAlgorithm"));

			this.maskGenAlgorithm = getParametersValue$1(parameters, "maskGenAlgorithm", RSASSAPSSParams.defaultValues("maskGenAlgorithm"));

			this.saltLength = getParametersValue$1(parameters, "saltLength", RSASSAPSSParams.defaultValues("saltLength"));

			this.trailerField = getParametersValue$1(parameters, "trailerField", RSASSAPSSParams.defaultValues("trailerField"));

			if ("schema" in parameters) this.fromSchema(parameters.schema);
		}

		_createClass(RSASSAPSSParams, [{
			key: 'fromSchema',
			value: function fromSchema(schema) {
				var asn1 = compareSchema(schema, schema, RSASSAPSSParams.schema({
					names: {
						hashAlgorithm: {
							names: {
								blockName: "hashAlgorithm"
							}
						},
						maskGenAlgorithm: {
							names: {
								blockName: "maskGenAlgorithm"
							}
						},
						saltLength: "saltLength",
						trailerField: "trailerField"
					}
				}));

				if (asn1.verified === false) throw new Error("Object's schema was not verified against input data for RSASSA_PSS_params");

				if ("hashAlgorithm" in asn1.result) this.hashAlgorithm = new AlgorithmIdentifier({ schema: asn1.result.hashAlgorithm });

				if ("maskGenAlgorithm" in asn1.result) this.maskGenAlgorithm = new AlgorithmIdentifier({ schema: asn1.result.maskGenAlgorithm });

				if ("saltLength" in asn1.result) this.saltLength = asn1.result.saltLength.valueBlock.valueDec;

				if ("trailerField" in asn1.result) this.trailerField = asn1.result.trailerField.valueBlock.valueDec;
			}
		}, {
			key: 'toSchema',
			value: function toSchema() {
				var outputArray = [];

				if (!this.hashAlgorithm.isEqual(RSASSAPSSParams.defaultValues("hashAlgorithm"))) {
					outputArray.push(new Constructed({
						idBlock: {
							tagClass: 3,
							tagNumber: 0 },
						value: [this.hashAlgorithm.toSchema()]
					}));
				}

				if (!this.maskGenAlgorithm.isEqual(RSASSAPSSParams.defaultValues("maskGenAlgorithm"))) {
					outputArray.push(new Constructed({
						idBlock: {
							tagClass: 3,
							tagNumber: 1 },
						value: [this.maskGenAlgorithm.toSchema()]
					}));
				}

				if (this.saltLength !== RSASSAPSSParams.defaultValues("saltLength")) {
					outputArray.push(new Constructed({
						idBlock: {
							tagClass: 3,
							tagNumber: 2 },
						value: [new Integer({ value: this.saltLength })]
					}));
				}

				if (this.trailerField !== RSASSAPSSParams.defaultValues("trailerField")) {
					outputArray.push(new Constructed({
						idBlock: {
							tagClass: 3,
							tagNumber: 3 },
						value: [new Integer({ value: this.trailerField })]
					}));
				}

				return new Sequence({
					value: outputArray
				});
			}
		}, {
			key: 'toJSON',
			value: function toJSON() {
				var object = {};

				if (!this.hashAlgorithm.isEqual(RSASSAPSSParams.defaultValues("hashAlgorithm"))) object.hashAlgorithm = this.hashAlgorithm.toJSON();

				if (!this.maskGenAlgorithm.isEqual(RSASSAPSSParams.defaultValues("maskGenAlgorithm"))) object.maskGenAlgorithm = this.maskGenAlgorithm.toJSON();

				if (this.saltLength !== RSASSAPSSParams.defaultValues("saltLength")) object.saltLength = this.saltLength;

				if (this.trailerField !== RSASSAPSSParams.defaultValues("trailerField")) object.trailerField = this.trailerField;

				return object;
			}
		}], [{
			key: 'defaultValues',
			value: function defaultValues(memberName) {
				switch (memberName) {
					case "hashAlgorithm":
						return new AlgorithmIdentifier({
							algorithmId: "1.3.14.3.2.26",
							algorithmParams: new Null()
						});
					case "maskGenAlgorithm":
						return new AlgorithmIdentifier({
							algorithmId: "1.2.840.113549.1.1.8",
							algorithmParams: new AlgorithmIdentifier({
								algorithmId: "1.3.14.3.2.26",
								algorithmParams: new Null()
							}).toSchema()
						});
					case "saltLength":
						return 20;
					case "trailerField":
						return 1;
					default:
						throw new Error('Invalid member name for RSASSAPSSParams class: ' + memberName);
				}
			}
		}, {
			key: 'schema',
			value: function schema() {
				var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

				var names = getParametersValue$1(parameters, "names", {});

				return new Sequence({
					name: names.blockName || "",
					value: [new Constructed({
						idBlock: {
							tagClass: 3,
							tagNumber: 0 },
						optional: true,
						value: [AlgorithmIdentifier.schema(names.hashAlgorithm || {})]
					}), new Constructed({
						idBlock: {
							tagClass: 3,
							tagNumber: 1 },
						optional: true,
						value: [AlgorithmIdentifier.schema(names.maskGenAlgorithm || {})]
					}), new Constructed({
						idBlock: {
							tagClass: 3,
							tagNumber: 2 },
						optional: true,
						value: [new Integer({ name: names.saltLength || "" })]
					}), new Constructed({
						idBlock: {
							tagClass: 3,
							tagNumber: 3 },
						optional: true,
						value: [new Integer({ name: names.trailerField || "" })]
					})]
				});
			}
		}]);

		return RSASSAPSSParams;
	}();

	var PBKDF2Params = function () {
		function PBKDF2Params() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, PBKDF2Params);

			this.salt = getParametersValue$1(parameters, "salt", PBKDF2Params.defaultValues("salt"));

			this.iterationCount = getParametersValue$1(parameters, "iterationCount", PBKDF2Params.defaultValues("iterationCount"));

			if ("keyLength" in parameters) this.keyLength = getParametersValue$1(parameters, "keyLength", PBKDF2Params.defaultValues("keyLength"));

			if ("prf" in parameters) this.prf = getParametersValue$1(parameters, "prf", PBKDF2Params.defaultValues("prf"));

			if ("schema" in parameters) this.fromSchema(parameters.schema);
		}

		_createClass(PBKDF2Params, [{
			key: 'fromSchema',
			value: function fromSchema(schema) {
				var asn1 = compareSchema(schema, schema, PBKDF2Params.schema({
					names: {
						saltPrimitive: "salt",
						saltConstructed: {
							names: {
								blockName: "salt"
							}
						},
						iterationCount: "iterationCount",
						keyLength: "keyLength",
						prf: {
							names: {
								blockName: "prf",
								optional: true
							}
						}
					}
				}));

				if (asn1.verified === false) throw new Error("Object's schema was not verified against input data for PBKDF2_params");

				this.salt = asn1.result.salt;
				this.iterationCount = asn1.result.iterationCount.valueBlock.valueDec;

				if ("keyLength" in asn1.result) this.keyLength = asn1.result.keyLength.valueBlock.valueDec;

				if ("prf" in asn1.result) this.prf = new AlgorithmIdentifier({ schema: asn1.result.prf });
			}
		}, {
			key: 'toSchema',
			value: function toSchema() {
				var outputArray = [];

				outputArray.push(this.salt);
				outputArray.push(new Integer({ value: this.iterationCount }));

				if ("keyLength" in this) {
					if (PBKDF2Params.defaultValues("keyLength") !== this.keyLength) outputArray.push(new Integer({ value: this.keyLength }));
				}

				if ("prf" in this) {
					if (PBKDF2Params.defaultValues("prf").isEqual(this.prf) === false) outputArray.push(this.prf.toSchema());
				}

				return new Sequence({
					value: outputArray
				});
			}
		}, {
			key: 'toJSON',
			value: function toJSON() {
				var _object = {
					salt: this.salt.toJSON(),
					iterationCount: this.iterationCount
				};

				if ("keyLength" in this) {
					if (PBKDF2Params.defaultValues("keyLength") !== this.keyLength) _object.keyLength = this.keyLength;
				}

				if ("prf" in this) {
					if (PBKDF2Params.defaultValues("prf").isEqual(this.prf) === false) _object.prf = this.prf.toJSON();
				}

				return _object;
			}
		}], [{
			key: 'defaultValues',
			value: function defaultValues(memberName) {
				switch (memberName) {
					case "salt":
						return {};
					case "iterationCount":
						return -1;
					case "keyLength":
						return 0;
					case "prf":
						return new AlgorithmIdentifier({
							algorithmId: "1.3.14.3.2.26",
							algorithmParams: new Null()
						});
					default:
						throw new Error('Invalid member name for PBKDF2Params class: ' + memberName);
				}
			}
		}, {
			key: 'schema',
			value: function schema() {
				var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

				var names = getParametersValue$1(parameters, "names", {});

				return new Sequence({
					name: names.blockName || "",
					value: [new Choice({
						value: [new OctetString({ name: names.saltPrimitive || "" }), AlgorithmIdentifier.schema(names.saltConstructed || {})]
					}), new Integer({ name: names.iterationCount || "" }), new Integer({
						name: names.keyLength || "",
						optional: true
					}), AlgorithmIdentifier.schema(names.prf || {
						names: {
							optional: true
						}
					})]
				});
			}
		}]);

		return PBKDF2Params;
	}();

	var PBES2Params = function () {
		function PBES2Params() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, PBES2Params);

			this.keyDerivationFunc = getParametersValue$1(parameters, "keyDerivationFunc", PBES2Params.defaultValues("keyDerivationFunc"));

			this.encryptionScheme = getParametersValue$1(parameters, "encryptionScheme", PBES2Params.defaultValues("encryptionScheme"));

			if ("schema" in parameters) this.fromSchema(parameters.schema);
		}

		_createClass(PBES2Params, [{
			key: 'fromSchema',
			value: function fromSchema(schema) {
				var asn1 = compareSchema(schema, schema, PBES2Params.schema({
					names: {
						keyDerivationFunc: {
							names: {
								blockName: "keyDerivationFunc"
							}
						},
						encryptionScheme: {
							names: {
								blockName: "encryptionScheme"
							}
						}
					}
				}));

				if (asn1.verified === false) throw new Error("Object's schema was not verified against input data for PBES2_params");

				this.keyDerivationFunc = new AlgorithmIdentifier({ schema: asn1.result.keyDerivationFunc });
				this.encryptionScheme = new AlgorithmIdentifier({ schema: asn1.result.encryptionScheme });
			}
		}, {
			key: 'toSchema',
			value: function toSchema() {
				return new Sequence({
					value: [this.keyDerivationFunc.toSchema(), this.encryptionScheme.toSchema()]
				});
			}
		}, {
			key: 'toJSON',
			value: function toJSON() {
				return {
					keyDerivationFunc: this.keyDerivationFunc.toJSON(),
					encryptionScheme: this.encryptionScheme.toJSON()
				};
			}
		}], [{
			key: 'defaultValues',
			value: function defaultValues(memberName) {
				switch (memberName) {
					case "keyDerivationFunc":
						return new AlgorithmIdentifier();
					case "encryptionScheme":
						return new AlgorithmIdentifier();
					default:
						throw new Error('Invalid member name for PBES2Params class: ' + memberName);
				}
			}
		}, {
			key: 'schema',
			value: function schema() {
				var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

				var names = getParametersValue$1(parameters, "names", {});

				return new Sequence({
					name: names.blockName || "",
					value: [AlgorithmIdentifier.schema(names.keyDerivationFunc || {}), AlgorithmIdentifier.schema(names.encryptionScheme || {})]
				});
			}
		}]);

		return PBES2Params;
	}();

	function makePKCS12B2Key(cryptoEngine, hashAlgorithm, keyLength, password, salt, iterationCount) {
		var u = void 0;
		var v = void 0;

		var result = [];

		switch (hashAlgorithm.toUpperCase()) {
			case "SHA-1":
				u = 20;
				v = 64;
				break;
			case "SHA-256":
				u = 32;
				v = 64;
				break;
			case "SHA-384":
				u = 48;
				v = 128;
				break;
			case "SHA-512":
				u = 64;
				v = 128;
				break;
			default:
				throw new Error("Unsupported hashing algorithm");
		}

		var passwordViewInitial = new Uint8Array(password);

		var passwordTransformed = new ArrayBuffer(password.byteLength * 2 + 2);
		var passwordTransformedView = new Uint8Array(passwordTransformed);

		for (var i = 0; i < passwordViewInitial.length; i++) {
			passwordTransformedView[i * 2] = 0x00;
			passwordTransformedView[i * 2 + 1] = passwordViewInitial[i];
		}

		passwordTransformedView[passwordTransformedView.length - 2] = 0x00;
		passwordTransformedView[passwordTransformedView.length - 1] = 0x00;

		password = passwordTransformed.slice(0);

		var D = new ArrayBuffer(v);
		var dView = new Uint8Array(D);

		for (var _i13 = 0; _i13 < D.byteLength; _i13++) {
			dView[_i13] = 3;
		}
		var saltLength = salt.byteLength;

		var sLen = v * Math.ceil(saltLength / v);
		var S = new ArrayBuffer(sLen);
		var sView = new Uint8Array(S);

		var saltView = new Uint8Array(salt);

		for (var _i14 = 0; _i14 < sLen; _i14++) {
			sView[_i14] = saltView[_i14 % saltLength];
		}
		var passwordLength = password.byteLength;

		var pLen = v * Math.ceil(passwordLength / v);
		var P = new ArrayBuffer(pLen);
		var pView = new Uint8Array(P);

		var passwordView = new Uint8Array(password);

		for (var _i15 = 0; _i15 < pLen; _i15++) {
			pView[_i15] = passwordView[_i15 % passwordLength];
		}
		var sPlusPLength = S.byteLength + P.byteLength;

		var I = new ArrayBuffer(sPlusPLength);
		var iView = new Uint8Array(I);

		iView.set(sView);
		iView.set(pView, sView.length);

		var c = Math.ceil((keyLength >> 3) / u);

		var internalSequence = Promise.resolve(I);

		for (var _i16 = 0; _i16 <= c; _i16++) {
			internalSequence = internalSequence.then(function (_I) {
				var dAndI = new ArrayBuffer(D.byteLength + _I.byteLength);
				var dAndIView = new Uint8Array(dAndI);

				dAndIView.set(dView);
				dAndIView.set(iView, dView.length);


				return dAndI;
			});

			for (var j = 0; j < iterationCount; j++) {
				internalSequence = internalSequence.then(function (roundBuffer) {
					return cryptoEngine.digest({ name: hashAlgorithm }, new Uint8Array(roundBuffer));
				});
			}

			internalSequence = internalSequence.then(function (roundBuffer) {
				var B = new ArrayBuffer(v);
				var bView = new Uint8Array(B);

				for (var _j = 0; _j < B.byteLength; _j++) {
					bView[_j] = roundBuffer[_j % roundBuffer.length];
				}
				var k = Math.ceil(saltLength / v) + Math.ceil(passwordLength / v);
				var iRound = [];

				var sliceStart = 0;
				var sliceLength = v;

				for (var _j2 = 0; _j2 < k; _j2++) {
					var chunk = Array.from(new Uint8Array(I.slice(sliceStart, sliceStart + sliceLength)));
					sliceStart += v;
					if (sliceStart + v > I.byteLength) sliceLength = I.byteLength - sliceStart;

					var x = 0x1ff;

					for (var l = B.byteLength - 1; l >= 0; l--) {
						x = x >> 8;
						x += bView[l] + chunk[l];
						chunk[l] = x & 0xff;
					}

					iRound.push.apply(iRound, _toConsumableArray(chunk));
				}

				I = new ArrayBuffer(iRound.length);
				iView = new Uint8Array(I);

				iView.set(iRound);


				result.push.apply(result, _toConsumableArray(new Uint8Array(roundBuffer)));

				return I;
			});
		}

		internalSequence = internalSequence.then(function () {
			var resultBuffer = new ArrayBuffer(keyLength >> 3);
			var resultView = new Uint8Array(resultBuffer);

			resultView.set(new Uint8Array(result).slice(0, keyLength >> 3));

			return resultBuffer;
		});


		return internalSequence;
	}

	var CryptoEngine = function () {
		function CryptoEngine() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, CryptoEngine);

			this.crypto = getParametersValue$1(parameters, "crypto", {});

			this.subtle = getParametersValue$1(parameters, "subtle", {});

			this.name = getParametersValue$1(parameters, "name", "");
		}

		_createClass(CryptoEngine, [{
			key: 'importKey',
			value: function importKey(format, keyData, algorithm, extractable, keyUsages) {
				var _this54 = this;

				var jwk = {};

				if (keyData instanceof Uint8Array) keyData = keyData.buffer;


				switch (format.toLowerCase()) {
					case "raw":
						return this.subtle.importKey("raw", keyData, algorithm, extractable, keyUsages);
					case "spki":
						{
							var asn1 = fromBER(keyData);
							if (asn1.offset === -1) return Promise.reject("Incorrect keyData");

							var publicKeyInfo = new PublicKeyInfo();
							try {
								publicKeyInfo.fromSchema(asn1.result);
							} catch (ex) {
								return Promise.reject("Incorrect keyData");
							}

							switch (algorithm.name.toUpperCase()) {
								case "RSA-PSS":
									{
										switch (algorithm.hash.name.toUpperCase()) {
											case "SHA-1":
												jwk.alg = "PS1";
												break;
											case "SHA-256":
												jwk.alg = "PS256";
												break;
											case "SHA-384":
												jwk.alg = "PS384";
												break;
											case "SHA-512":
												jwk.alg = "PS512";
												break;
											default:
												return Promise.reject('Incorrect hash algorithm: ' + algorithm.hash.name.toUpperCase());
										}
									}
								case "RSASSA-PKCS1-V1_5":
									{
										keyUsages = ["verify"];

										jwk.kty = "RSA";
										jwk.ext = extractable;
										jwk.key_ops = keyUsages;

										if (publicKeyInfo.algorithm.algorithmId !== "1.2.840.113549.1.1.1") return Promise.reject('Incorrect public key algorithm: ' + publicKeyInfo.algorithm.algorithmId);

										if ("alg" in jwk === false) {
											switch (algorithm.hash.name.toUpperCase()) {
												case "SHA-1":
													jwk.alg = "RS1";
													break;
												case "SHA-256":
													jwk.alg = "RS256";
													break;
												case "SHA-384":
													jwk.alg = "RS384";
													break;
												case "SHA-512":
													jwk.alg = "RS512";
													break;
												default:
													return Promise.reject('Incorrect public key algorithm: ' + publicKeyInfo.algorithm.algorithmId);
											}
										}

										var publicKeyJSON = publicKeyInfo.toJSON();

										var _iteratorNormalCompletion13 = true;
										var _didIteratorError13 = false;
										var _iteratorError13 = undefined;

										try {
											for (var _iterator13 = Object.keys(publicKeyJSON)[Symbol.iterator](), _step13; !(_iteratorNormalCompletion13 = (_step13 = _iterator13.next()).done); _iteratorNormalCompletion13 = true) {
												var key = _step13.value;

												jwk[key] = publicKeyJSON[key];
											}
										} catch (err) {
											_didIteratorError13 = true;
											_iteratorError13 = err;
										} finally {
											try {
												if (!_iteratorNormalCompletion13 && _iterator13.return) {
													_iterator13.return();
												}
											} finally {
												if (_didIteratorError13) {
													throw _iteratorError13;
												}
											}
										}
									}
									break;
								case "ECDSA":
									keyUsages = ["verify"];
								case "ECDH":
									{
										jwk = {
											kty: "EC",
											ext: extractable,
											key_ops: keyUsages
										};

										if (publicKeyInfo.algorithm.algorithmId !== "1.2.840.10045.2.1") return Promise.reject('Incorrect public key algorithm: ' + publicKeyInfo.algorithm.algorithmId);

										var _publicKeyJSON = publicKeyInfo.toJSON();

										var _iteratorNormalCompletion14 = true;
										var _didIteratorError14 = false;
										var _iteratorError14 = undefined;

										try {
											for (var _iterator14 = Object.keys(_publicKeyJSON)[Symbol.iterator](), _step14; !(_iteratorNormalCompletion14 = (_step14 = _iterator14.next()).done); _iteratorNormalCompletion14 = true) {
												var _key8 = _step14.value;

												jwk[_key8] = _publicKeyJSON[_key8];
											}
										} catch (err) {
											_didIteratorError14 = true;
											_iteratorError14 = err;
										} finally {
											try {
												if (!_iteratorNormalCompletion14 && _iterator14.return) {
													_iterator14.return();
												}
											} finally {
												if (_didIteratorError14) {
													throw _iteratorError14;
												}
											}
										}
									}
									break;
								case "RSA-OAEP":
									{
										jwk.kty = "RSA";
										jwk.ext = extractable;
										jwk.key_ops = keyUsages;

										if (this.name.toLowerCase() === "safari") jwk.alg = "RSA-OAEP";else {
											switch (algorithm.hash.name.toUpperCase()) {
												case "SHA-1":
													jwk.alg = "RSA-OAEP-1";
													break;
												case "SHA-256":
													jwk.alg = "RSA-OAEP-256";
													break;
												case "SHA-384":
													jwk.alg = "RSA-OAEP-384";
													break;
												case "SHA-512":
													jwk.alg = "RSA-OAEP-512";
													break;
												default:
													return Promise.reject('Incorrect public key algorithm: ' + publicKeyInfo.algorithm.algorithmId);
											}
										}

										var _publicKeyJSON2 = publicKeyInfo.toJSON();

										var _iteratorNormalCompletion15 = true;
										var _didIteratorError15 = false;
										var _iteratorError15 = undefined;

										try {
											for (var _iterator15 = Object.keys(_publicKeyJSON2)[Symbol.iterator](), _step15; !(_iteratorNormalCompletion15 = (_step15 = _iterator15.next()).done); _iteratorNormalCompletion15 = true) {
												var _key9 = _step15.value;

												jwk[_key9] = _publicKeyJSON2[_key9];
											}
										} catch (err) {
											_didIteratorError15 = true;
											_iteratorError15 = err;
										} finally {
											try {
												if (!_iteratorNormalCompletion15 && _iterator15.return) {
													_iterator15.return();
												}
											} finally {
												if (_didIteratorError15) {
													throw _iteratorError15;
												}
											}
										}
									}
									break;
								default:
									return Promise.reject('Incorrect algorithm name: ' + algorithm.name.toUpperCase());
							}
						}
						break;
					case "pkcs8":
						{
							var privateKeyInfo = new PrivateKeyInfo();

							var _asn = fromBER(keyData);
							if (_asn.offset === -1) return Promise.reject("Incorrect keyData");

							try {
								privateKeyInfo.fromSchema(_asn.result);
							} catch (ex) {
								return Promise.reject("Incorrect keyData");
							}


							switch (algorithm.name.toUpperCase()) {
								case "RSA-PSS":
									{
										switch (algorithm.hash.name.toUpperCase()) {
											case "SHA-1":
												jwk.alg = "PS1";
												break;
											case "SHA-256":
												jwk.alg = "PS256";
												break;
											case "SHA-384":
												jwk.alg = "PS384";
												break;
											case "SHA-512":
												jwk.alg = "PS512";
												break;
											default:
												return Promise.reject('Incorrect hash algorithm: ' + algorithm.hash.name.toUpperCase());
										}
									}
								case "RSASSA-PKCS1-V1_5":
									{
										keyUsages = ["sign"];

										jwk.kty = "RSA";
										jwk.ext = extractable;
										jwk.key_ops = keyUsages;

										if (privateKeyInfo.privateKeyAlgorithm.algorithmId !== "1.2.840.113549.1.1.1") return Promise.reject('Incorrect private key algorithm: ' + privateKeyInfo.privateKeyAlgorithm.algorithmId);

										if ("alg" in jwk === false) {
											switch (algorithm.hash.name.toUpperCase()) {
												case "SHA-1":
													jwk.alg = "RS1";
													break;
												case "SHA-256":
													jwk.alg = "RS256";
													break;
												case "SHA-384":
													jwk.alg = "RS384";
													break;
												case "SHA-512":
													jwk.alg = "RS512";
													break;
												default:
													return Promise.reject('Incorrect hash algorithm: ' + algorithm.hash.name.toUpperCase());
											}
										}

										var privateKeyJSON = privateKeyInfo.toJSON();

										var _iteratorNormalCompletion16 = true;
										var _didIteratorError16 = false;
										var _iteratorError16 = undefined;

										try {
											for (var _iterator16 = Object.keys(privateKeyJSON)[Symbol.iterator](), _step16; !(_iteratorNormalCompletion16 = (_step16 = _iterator16.next()).done); _iteratorNormalCompletion16 = true) {
												var _key10 = _step16.value;

												jwk[_key10] = privateKeyJSON[_key10];
											}
										} catch (err) {
											_didIteratorError16 = true;
											_iteratorError16 = err;
										} finally {
											try {
												if (!_iteratorNormalCompletion16 && _iterator16.return) {
													_iterator16.return();
												}
											} finally {
												if (_didIteratorError16) {
													throw _iteratorError16;
												}
											}
										}
									}
									break;
								case "ECDSA":
									keyUsages = ["sign"];
								case "ECDH":
									{
										jwk = {
											kty: "EC",
											ext: extractable,
											key_ops: keyUsages
										};

										if (privateKeyInfo.privateKeyAlgorithm.algorithmId !== "1.2.840.10045.2.1") return Promise.reject('Incorrect algorithm: ' + privateKeyInfo.privateKeyAlgorithm.algorithmId);

										var _privateKeyJSON = privateKeyInfo.toJSON();

										var _iteratorNormalCompletion17 = true;
										var _didIteratorError17 = false;
										var _iteratorError17 = undefined;

										try {
											for (var _iterator17 = Object.keys(_privateKeyJSON)[Symbol.iterator](), _step17; !(_iteratorNormalCompletion17 = (_step17 = _iterator17.next()).done); _iteratorNormalCompletion17 = true) {
												var _key11 = _step17.value;

												jwk[_key11] = _privateKeyJSON[_key11];
											}
										} catch (err) {
											_didIteratorError17 = true;
											_iteratorError17 = err;
										} finally {
											try {
												if (!_iteratorNormalCompletion17 && _iterator17.return) {
													_iterator17.return();
												}
											} finally {
												if (_didIteratorError17) {
													throw _iteratorError17;
												}
											}
										}
									}
									break;
								case "RSA-OAEP":
									{
										jwk.kty = "RSA";
										jwk.ext = extractable;
										jwk.key_ops = keyUsages;

										if (this.name.toLowerCase() === "safari") jwk.alg = "RSA-OAEP";else {
											switch (algorithm.hash.name.toUpperCase()) {
												case "SHA-1":
													jwk.alg = "RSA-OAEP-1";
													break;
												case "SHA-256":
													jwk.alg = "RSA-OAEP-256";
													break;
												case "SHA-384":
													jwk.alg = "RSA-OAEP-384";
													break;
												case "SHA-512":
													jwk.alg = "RSA-OAEP-512";
													break;
												default:
													return Promise.reject('Incorrect hash algorithm: ' + algorithm.hash.name.toUpperCase());
											}
										}

										var _privateKeyJSON2 = privateKeyInfo.toJSON();

										var _iteratorNormalCompletion18 = true;
										var _didIteratorError18 = false;
										var _iteratorError18 = undefined;

										try {
											for (var _iterator18 = Object.keys(_privateKeyJSON2)[Symbol.iterator](), _step18; !(_iteratorNormalCompletion18 = (_step18 = _iterator18.next()).done); _iteratorNormalCompletion18 = true) {
												var _key12 = _step18.value;

												jwk[_key12] = _privateKeyJSON2[_key12];
											}
										} catch (err) {
											_didIteratorError18 = true;
											_iteratorError18 = err;
										} finally {
											try {
												if (!_iteratorNormalCompletion18 && _iterator18.return) {
													_iterator18.return();
												}
											} finally {
												if (_didIteratorError18) {
													throw _iteratorError18;
												}
											}
										}
									}
									break;
								default:
									return Promise.reject('Incorrect algorithm name: ' + algorithm.name.toUpperCase());
							}
						}
						break;
					case "jwk":
						jwk = keyData;
						break;
					default:
						return Promise.reject('Incorrect format: ' + format);
				}

				if (this.name.toLowerCase() === "safari") {
					return Promise.resolve().then(function () {
						return _this54.subtle.importKey("jwk", stringToArrayBuffer$1(JSON.stringify(jwk)), algorithm, extractable, keyUsages);
					}).then(function (result) {
						return result;
					}, function (error) {
						return _this54.subtle.importKey("jwk", jwk, algorithm, extractable, keyUsages);
					});
				}


				return this.subtle.importKey("jwk", jwk, algorithm, extractable, keyUsages);
			}
		}, {
			key: 'exportKey',
			value: function exportKey(format, key) {
				var sequence = this.subtle.exportKey("jwk", key);

				if (this.name.toLowerCase() === "safari") {
					sequence = sequence.then(function (result) {
						if (result instanceof ArrayBuffer) return JSON.parse(arrayBufferToString$1(result));

						return result;
					});
				}


				switch (format.toLowerCase()) {
					case "raw":
						return this.subtle.exportKey("raw", key);
					case "spki":
						sequence = sequence.then(function (result) {
							var publicKeyInfo = new PublicKeyInfo();

							try {
								publicKeyInfo.fromJSON(result);
							} catch (ex) {
								return Promise.reject("Incorrect key data");
							}

							return publicKeyInfo.toSchema().toBER(false);
						});
						break;
					case "pkcs8":
						sequence = sequence.then(function (result) {
							var privateKeyInfo = new PrivateKeyInfo();

							try {
								privateKeyInfo.fromJSON(result);
							} catch (ex) {
								return Promise.reject("Incorrect key data");
							}

							return privateKeyInfo.toSchema().toBER(false);
						});
						break;
					case "jwk":
						break;
					default:
						return Promise.reject('Incorrect format: ' + format);
				}

				return sequence;
			}
		}, {
			key: 'convert',
			value: function convert(inputFormat, outputFormat, keyData, algorithm, extractable, keyUsages) {
				var _this55 = this;

				switch (inputFormat.toLowerCase()) {
					case "raw":
						switch (outputFormat.toLowerCase()) {
							case "raw":
								return Promise.resolve(keyData);
							case "spki":
								return Promise.resolve().then(function () {
									return _this55.importKey("raw", keyData, algorithm, extractable, keyUsages);
								}).then(function (result) {
									return _this55.exportKey("spki", result);
								});
							case "pkcs8":
								return Promise.resolve().then(function () {
									return _this55.importKey("raw", keyData, algorithm, extractable, keyUsages);
								}).then(function (result) {
									return _this55.exportKey("pkcs8", result);
								});
							case "jwk":
								return Promise.resolve().then(function () {
									return _this55.importKey("raw", keyData, algorithm, extractable, keyUsages);
								}).then(function (result) {
									return _this55.exportKey("jwk", result);
								});
							default:
								return Promise.reject('Incorrect outputFormat: ' + outputFormat);
						}
					case "spki":
						switch (outputFormat.toLowerCase()) {
							case "raw":
								return Promise.resolve().then(function () {
									return _this55.importKey("spki", keyData, algorithm, extractable, keyUsages);
								}).then(function (result) {
									return _this55.exportKey("raw", result);
								});
							case "spki":
								return Promise.resolve(keyData);
							case "pkcs8":
								return Promise.reject("Impossible to convert between SPKI/PKCS8");
							case "jwk":
								return Promise.resolve().then(function () {
									return _this55.importKey("spki", keyData, algorithm, extractable, keyUsages);
								}).then(function (result) {
									return _this55.exportKey("jwk", result);
								});
							default:
								return Promise.reject('Incorrect outputFormat: ' + outputFormat);
						}
					case "pkcs8":
						switch (outputFormat.toLowerCase()) {
							case "raw":
								return Promise.resolve().then(function () {
									return _this55.importKey("pkcs8", keyData, algorithm, extractable, keyUsages);
								}).then(function (result) {
									return _this55.exportKey("raw", result);
								});
							case "spki":
								return Promise.reject("Impossible to convert between SPKI/PKCS8");
							case "pkcs8":
								return Promise.resolve(keyData);
							case "jwk":
								return Promise.resolve().then(function () {
									return _this55.importKey("pkcs8", keyData, algorithm, extractable, keyUsages);
								}).then(function (result) {
									return _this55.exportKey("jwk", result);
								});
							default:
								return Promise.reject('Incorrect outputFormat: ' + outputFormat);
						}
					case "jwk":
						switch (outputFormat.toLowerCase()) {
							case "raw":
								return Promise.resolve().then(function () {
									return _this55.importKey("jwk", keyData, algorithm, extractable, keyUsages);
								}).then(function (result) {
									return _this55.exportKey("raw", result);
								});
							case "spki":
								return Promise.resolve().then(function () {
									return _this55.importKey("jwk", keyData, algorithm, extractable, keyUsages);
								}).then(function (result) {
									return _this55.exportKey("spki", result);
								});
							case "pkcs8":
								return Promise.resolve().then(function () {
									return _this55.importKey("jwk", keyData, algorithm, extractable, keyUsages);
								}).then(function (result) {
									return _this55.exportKey("pkcs8", result);
								});
							case "jwk":
								return Promise.resolve(keyData);
							default:
								return Promise.reject('Incorrect outputFormat: ' + outputFormat);
						}
					default:
						return Promise.reject('Incorrect inputFormat: ' + inputFormat);
				}
			}
		}, {
			key: 'encrypt',
			value: function encrypt() {
				var _subtle;

				return (_subtle = this.subtle).encrypt.apply(_subtle, arguments);
			}
		}, {
			key: 'decrypt',
			value: function decrypt() {
				var _subtle2;

				return (_subtle2 = this.subtle).decrypt.apply(_subtle2, arguments);
			}
		}, {
			key: 'sign',
			value: function sign() {
				var _subtle3;

				return (_subtle3 = this.subtle).sign.apply(_subtle3, arguments);
			}
		}, {
			key: 'verify',
			value: function verify() {
				var _subtle4;

				return (_subtle4 = this.subtle).verify.apply(_subtle4, arguments);
			}
		}, {
			key: 'digest',
			value: function digest() {
				var _subtle5;

				return (_subtle5 = this.subtle).digest.apply(_subtle5, arguments);
			}
		}, {
			key: 'generateKey',
			value: function generateKey() {
				var _subtle6;

				return (_subtle6 = this.subtle).generateKey.apply(_subtle6, arguments);
			}
		}, {
			key: 'deriveKey',
			value: function deriveKey() {
				var _subtle7;

				return (_subtle7 = this.subtle).deriveKey.apply(_subtle7, arguments);
			}
		}, {
			key: 'deriveBits',
			value: function deriveBits() {
				var _subtle8;

				return (_subtle8 = this.subtle).deriveBits.apply(_subtle8, arguments);
			}
		}, {
			key: 'wrapKey',
			value: function wrapKey() {
				var _subtle9;

				return (_subtle9 = this.subtle).wrapKey.apply(_subtle9, arguments);
			}
		}, {
			key: 'unwrapKey',
			value: function unwrapKey() {
				var _subtle10;

				return (_subtle10 = this.subtle).unwrapKey.apply(_subtle10, arguments);
			}
		}, {
			key: 'getRandomValues',
			value: function getRandomValues(view) {
				if ("getRandomValues" in this.crypto === false) throw new Error("No support for getRandomValues");

				return this.crypto.getRandomValues(view);
			}
		}, {
			key: 'getAlgorithmByOID',
			value: function getAlgorithmByOID(oid) {
				switch (oid) {
					case "1.2.840.113549.1.1.1":
					case "1.2.840.113549.1.1.5":
						return {
							name: "RSASSA-PKCS1-v1_5",
							hash: {
								name: "SHA-1"
							}
						};
					case "1.2.840.113549.1.1.11":
						return {
							name: "RSASSA-PKCS1-v1_5",
							hash: {
								name: "SHA-256"
							}
						};
					case "1.2.840.113549.1.1.12":
						return {
							name: "RSASSA-PKCS1-v1_5",
							hash: {
								name: "SHA-384"
							}
						};
					case "1.2.840.113549.1.1.13":
						return {
							name: "RSASSA-PKCS1-v1_5",
							hash: {
								name: "SHA-512"
							}
						};
					case "1.2.840.113549.1.1.10":
						return {
							name: "RSA-PSS"
						};
					case "1.2.840.113549.1.1.7":
						return {
							name: "RSA-OAEP"
						};
					case "1.2.840.10045.2.1":
					case "1.2.840.10045.4.1":
						return {
							name: "ECDSA",
							hash: {
								name: "SHA-1"
							}
						};
					case "1.2.840.10045.4.3.2":
						return {
							name: "ECDSA",
							hash: {
								name: "SHA-256"
							}
						};
					case "1.2.840.10045.4.3.3":
						return {
							name: "ECDSA",
							hash: {
								name: "SHA-384"
							}
						};
					case "1.2.840.10045.4.3.4":
						return {
							name: "ECDSA",
							hash: {
								name: "SHA-512"
							}
						};
					case "1.3.133.16.840.63.0.2":
						return {
							name: "ECDH",
							kdf: "SHA-1"
						};
					case "1.3.132.1.11.1":
						return {
							name: "ECDH",
							kdf: "SHA-256"
						};
					case "1.3.132.1.11.2":
						return {
							name: "ECDH",
							kdf: "SHA-384"
						};
					case "1.3.132.1.11.3":
						return {
							name: "ECDH",
							kdf: "SHA-512"
						};
					case "2.16.840.1.101.3.4.1.2":
						return {
							name: "AES-CBC",
							length: 128
						};
					case "2.16.840.1.101.3.4.1.22":
						return {
							name: "AES-CBC",
							length: 192
						};
					case "2.16.840.1.101.3.4.1.42":
						return {
							name: "AES-CBC",
							length: 256
						};
					case "2.16.840.1.101.3.4.1.6":
						return {
							name: "AES-GCM",
							length: 128
						};
					case "2.16.840.1.101.3.4.1.26":
						return {
							name: "AES-GCM",
							length: 192
						};
					case "2.16.840.1.101.3.4.1.46":
						return {
							name: "AES-GCM",
							length: 256
						};
					case "2.16.840.1.101.3.4.1.4":
						return {
							name: "AES-CFB",
							length: 128
						};
					case "2.16.840.1.101.3.4.1.24":
						return {
							name: "AES-CFB",
							length: 192
						};
					case "2.16.840.1.101.3.4.1.44":
						return {
							name: "AES-CFB",
							length: 256
						};
					case "2.16.840.1.101.3.4.1.5":
						return {
							name: "AES-KW",
							length: 128
						};
					case "2.16.840.1.101.3.4.1.25":
						return {
							name: "AES-KW",
							length: 192
						};
					case "2.16.840.1.101.3.4.1.45":
						return {
							name: "AES-KW",
							length: 256
						};
					case "1.2.840.113549.2.7":
						return {
							name: "HMAC",
							hash: {
								name: "SHA-1"
							}
						};
					case "1.2.840.113549.2.9":
						return {
							name: "HMAC",
							hash: {
								name: "SHA-256"
							}
						};
					case "1.2.840.113549.2.10":
						return {
							name: "HMAC",
							hash: {
								name: "SHA-384"
							}
						};
					case "1.2.840.113549.2.11":
						return {
							name: "HMAC",
							hash: {
								name: "SHA-512"
							}
						};
					case "1.2.840.113549.1.9.16.3.5":
						return {
							name: "DH"
						};
					case "1.3.14.3.2.26":
						return {
							name: "SHA-1"
						};
					case "2.16.840.1.101.3.4.2.1":
						return {
							name: "SHA-256"
						};
					case "2.16.840.1.101.3.4.2.2":
						return {
							name: "SHA-384"
						};
					case "2.16.840.1.101.3.4.2.3":
						return {
							name: "SHA-512"
						};
					case "1.2.840.113549.1.5.12":
						return {
							name: "PBKDF2"
						};

					case "1.2.840.10045.3.1.7":
						return {
							name: "P-256"
						};
					case "1.3.132.0.34":
						return {
							name: "P-384"
						};
					case "1.3.132.0.35":
						return {
							name: "P-521"
						};

					default:
				}

				return {};
			}
		}, {
			key: 'getOIDByAlgorithm',
			value: function getOIDByAlgorithm(algorithm) {
				var result = "";

				switch (algorithm.name.toUpperCase()) {
					case "RSASSA-PKCS1-V1_5":
						switch (algorithm.hash.name.toUpperCase()) {
							case "SHA-1":
								result = "1.2.840.113549.1.1.5";
								break;
							case "SHA-256":
								result = "1.2.840.113549.1.1.11";
								break;
							case "SHA-384":
								result = "1.2.840.113549.1.1.12";
								break;
							case "SHA-512":
								result = "1.2.840.113549.1.1.13";
								break;
							default:
						}
						break;
					case "RSA-PSS":
						result = "1.2.840.113549.1.1.10";
						break;
					case "RSA-OAEP":
						result = "1.2.840.113549.1.1.7";
						break;
					case "ECDSA":
						switch (algorithm.hash.name.toUpperCase()) {
							case "SHA-1":
								result = "1.2.840.10045.4.1";
								break;
							case "SHA-256":
								result = "1.2.840.10045.4.3.2";
								break;
							case "SHA-384":
								result = "1.2.840.10045.4.3.3";
								break;
							case "SHA-512":
								result = "1.2.840.10045.4.3.4";
								break;
							default:
						}
						break;
					case "ECDH":
						switch (algorithm.kdf.toUpperCase()) {
							case "SHA-1":
								result = "1.3.133.16.840.63.0.2";
								break;
							case "SHA-256":
								result = "1.3.132.1.11.1";
								break;
							case "SHA-384":
								result = "1.3.132.1.11.2";
								break;
							case "SHA-512":
								result = "1.3.132.1.11.3";
								break;
							default:
						}
						break;
					case "AES-CTR":
						break;
					case "AES-CBC":
						switch (algorithm.length) {
							case 128:
								result = "2.16.840.1.101.3.4.1.2";
								break;
							case 192:
								result = "2.16.840.1.101.3.4.1.22";
								break;
							case 256:
								result = "2.16.840.1.101.3.4.1.42";
								break;
							default:
						}
						break;
					case "AES-CMAC":
						break;
					case "AES-GCM":
						switch (algorithm.length) {
							case 128:
								result = "2.16.840.1.101.3.4.1.6";
								break;
							case 192:
								result = "2.16.840.1.101.3.4.1.26";
								break;
							case 256:
								result = "2.16.840.1.101.3.4.1.46";
								break;
							default:
						}
						break;
					case "AES-CFB":
						switch (algorithm.length) {
							case 128:
								result = "2.16.840.1.101.3.4.1.4";
								break;
							case 192:
								result = "2.16.840.1.101.3.4.1.24";
								break;
							case 256:
								result = "2.16.840.1.101.3.4.1.44";
								break;
							default:
						}
						break;
					case "AES-KW":
						switch (algorithm.length) {
							case 128:
								result = "2.16.840.1.101.3.4.1.5";
								break;
							case 192:
								result = "2.16.840.1.101.3.4.1.25";
								break;
							case 256:
								result = "2.16.840.1.101.3.4.1.45";
								break;
							default:
						}
						break;
					case "HMAC":
						switch (algorithm.hash.name.toUpperCase()) {
							case "SHA-1":
								result = "1.2.840.113549.2.7";
								break;
							case "SHA-256":
								result = "1.2.840.113549.2.9";
								break;
							case "SHA-384":
								result = "1.2.840.113549.2.10";
								break;
							case "SHA-512":
								result = "1.2.840.113549.2.11";
								break;
							default:
						}
						break;
					case "DH":
						result = "1.2.840.113549.1.9.16.3.5";
						break;
					case "SHA-1":
						result = "1.3.14.3.2.26";
						break;
					case "SHA-256":
						result = "2.16.840.1.101.3.4.2.1";
						break;
					case "SHA-384":
						result = "2.16.840.1.101.3.4.2.2";
						break;
					case "SHA-512":
						result = "2.16.840.1.101.3.4.2.3";
						break;
					case "CONCAT":
						break;
					case "HKDF":
						break;
					case "PBKDF2":
						result = "1.2.840.113549.1.5.12";
						break;

					case "P-256":
						result = "1.2.840.10045.3.1.7";
						break;
					case "P-384":
						result = "1.3.132.0.34";
						break;
					case "P-521":
						result = "1.3.132.0.35";
						break;

					default:
				}

				return result;
			}
		}, {
			key: 'getAlgorithmParameters',
			value: function getAlgorithmParameters(algorithmName, operation) {
				var result = {
					algorithm: {},
					usages: []
				};

				switch (algorithmName.toUpperCase()) {
					case "RSASSA-PKCS1-V1_5":
						switch (operation.toLowerCase()) {
							case "generatekey":
								result = {
									algorithm: {
										name: "RSASSA-PKCS1-v1_5",
										modulusLength: 2048,
										publicExponent: new Uint8Array([0x01, 0x00, 0x01]),
										hash: {
											name: "SHA-256"
										}
									},
									usages: ["sign", "verify"]
								};
								break;
							case "verify":
							case "sign":
							case "importkey":
								result = {
									algorithm: {
										name: "RSASSA-PKCS1-v1_5",
										hash: {
											name: "SHA-256"
										}
									},
									usages: ["verify"] };
								break;
							case "exportkey":
							default:
								return {
									algorithm: {
										name: "RSASSA-PKCS1-v1_5"
									},
									usages: []
								};
						}
						break;
					case "RSA-PSS":
						switch (operation.toLowerCase()) {
							case "sign":
							case "verify":
								result = {
									algorithm: {
										name: "RSA-PSS",
										hash: {
											name: "SHA-1"
										},
										saltLength: 20
									},
									usages: ["sign", "verify"]
								};
								break;
							case "generatekey":
								result = {
									algorithm: {
										name: "RSA-PSS",
										modulusLength: 2048,
										publicExponent: new Uint8Array([0x01, 0x00, 0x01]),
										hash: {
											name: "SHA-1"
										}
									},
									usages: ["sign", "verify"]
								};
								break;
							case "importkey":
								result = {
									algorithm: {
										name: "RSA-PSS",
										hash: {
											name: "SHA-1"
										}
									},
									usages: ["verify"] };
								break;
							case "exportkey":
							default:
								return {
									algorithm: {
										name: "RSA-PSS"
									},
									usages: []
								};
						}
						break;
					case "RSA-OAEP":
						switch (operation.toLowerCase()) {
							case "encrypt":
							case "decrypt":
								result = {
									algorithm: {
										name: "RSA-OAEP"
									},
									usages: ["encrypt", "decrypt"]
								};
								break;
							case "generatekey":
								result = {
									algorithm: {
										name: "RSA-OAEP",
										modulusLength: 2048,
										publicExponent: new Uint8Array([0x01, 0x00, 0x01]),
										hash: {
											name: "SHA-256"
										}
									},
									usages: ["encrypt", "decrypt", "wrapKey", "unwrapKey"]
								};
								break;
							case "importkey":
								result = {
									algorithm: {
										name: "RSA-OAEP",
										hash: {
											name: "SHA-256"
										}
									},
									usages: ["encrypt"] };
								break;
							case "exportkey":
							default:
								return {
									algorithm: {
										name: "RSA-OAEP"
									},
									usages: []
								};
						}
						break;
					case "ECDSA":
						switch (operation.toLowerCase()) {
							case "generatekey":
								result = {
									algorithm: {
										name: "ECDSA",
										namedCurve: "P-256"
									},
									usages: ["sign", "verify"]
								};
								break;
							case "importkey":
								result = {
									algorithm: {
										name: "ECDSA",
										namedCurve: "P-256"
									},
									usages: ["verify"] };
								break;
							case "verify":
							case "sign":
								result = {
									algorithm: {
										name: "ECDSA",
										hash: {
											name: "SHA-256"
										}
									},
									usages: ["sign"]
								};
								break;
							default:
								return {
									algorithm: {
										name: "ECDSA"
									},
									usages: []
								};
						}
						break;
					case "ECDH":
						switch (operation.toLowerCase()) {
							case "exportkey":
							case "importkey":
							case "generatekey":
								result = {
									algorithm: {
										name: "ECDH",
										namedCurve: "P-256"
									},
									usages: ["deriveKey", "deriveBits"]
								};
								break;
							case "derivekey":
							case "derivebits":
								result = {
									algorithm: {
										name: "ECDH",
										namedCurve: "P-256",
										public: [] },
									usages: ["encrypt", "decrypt"]
								};
								break;
							default:
								return {
									algorithm: {
										name: "ECDH"
									},
									usages: []
								};
						}
						break;
					case "AES-CTR":
						switch (operation.toLowerCase()) {
							case "importkey":
							case "exportkey":
							case "generatekey":
								result = {
									algorithm: {
										name: "AES-CTR",
										length: 256
									},
									usages: ["encrypt", "decrypt", "wrapKey", "unwrapKey"]
								};
								break;
							case "decrypt":
							case "encrypt":
								result = {
									algorithm: {
										name: "AES-CTR",
										counter: new Uint8Array(16),
										length: 10
									},
									usages: ["encrypt", "decrypt", "wrapKey", "unwrapKey"]
								};
								break;
							default:
								return {
									algorithm: {
										name: "AES-CTR"
									},
									usages: []
								};
						}
						break;
					case "AES-CBC":
						switch (operation.toLowerCase()) {
							case "importkey":
							case "exportkey":
							case "generatekey":
								result = {
									algorithm: {
										name: "AES-CBC",
										length: 256
									},
									usages: ["encrypt", "decrypt", "wrapKey", "unwrapKey"]
								};
								break;
							case "decrypt":
							case "encrypt":
								result = {
									algorithm: {
										name: "AES-CBC",
										iv: this.getRandomValues(new Uint8Array(16)) },
									usages: ["encrypt", "decrypt", "wrapKey", "unwrapKey"]
								};
								break;
							default:
								return {
									algorithm: {
										name: "AES-CBC"
									},
									usages: []
								};
						}
						break;
					case "AES-GCM":
						switch (operation.toLowerCase()) {
							case "importkey":
							case "exportkey":
							case "generatekey":
								result = {
									algorithm: {
										name: "AES-GCM",
										length: 256
									},
									usages: ["encrypt", "decrypt", "wrapKey", "unwrapKey"]
								};
								break;
							case "decrypt":
							case "encrypt":
								result = {
									algorithm: {
										name: "AES-GCM",
										iv: this.getRandomValues(new Uint8Array(16)) },
									usages: ["encrypt", "decrypt", "wrapKey", "unwrapKey"]
								};
								break;
							default:
								return {
									algorithm: {
										name: "AES-GCM"
									},
									usages: []
								};
						}
						break;
					case "AES-KW":
						switch (operation.toLowerCase()) {
							case "importkey":
							case "exportkey":
							case "generatekey":
							case "wrapkey":
							case "unwrapkey":
								result = {
									algorithm: {
										name: "AES-KW",
										length: 256
									},
									usages: ["wrapKey", "unwrapKey"]
								};
								break;
							default:
								return {
									algorithm: {
										name: "AES-KW"
									},
									usages: []
								};
						}
						break;
					case "HMAC":
						switch (operation.toLowerCase()) {
							case "sign":
							case "verify":
								result = {
									algorithm: {
										name: "HMAC"
									},
									usages: ["sign", "verify"]
								};
								break;
							case "importkey":
							case "exportkey":
							case "generatekey":
								result = {
									algorithm: {
										name: "HMAC",
										length: 32,
										hash: {
											name: "SHA-256"
										}
									},
									usages: ["sign", "verify"]
								};
								break;
							default:
								return {
									algorithm: {
										name: "HMAC"
									},
									usages: []
								};
						}
						break;
					case "HKDF":
						switch (operation.toLowerCase()) {
							case "derivekey":
								result = {
									algorithm: {
										name: "HKDF",
										hash: "SHA-256",
										salt: new Uint8Array([]),
										info: new Uint8Array([])
									},
									usages: ["encrypt", "decrypt"]
								};
								break;
							default:
								return {
									algorithm: {
										name: "HKDF"
									},
									usages: []
								};
						}
						break;
					case "PBKDF2":
						switch (operation.toLowerCase()) {
							case "derivekey":
								result = {
									algorithm: {
										name: "PBKDF2",
										hash: { name: "SHA-256" },
										salt: new Uint8Array([]),
										iterations: 10000
									},
									usages: ["encrypt", "decrypt"]
								};
								break;
							default:
								return {
									algorithm: {
										name: "PBKDF2"
									},
									usages: []
								};
						}
						break;
					default:
				}

				return result;
			}
		}, {
			key: 'getHashAlgorithm',
			value: function getHashAlgorithm(signatureAlgorithm) {
				var result = "";

				switch (signatureAlgorithm.algorithmId) {
					case "1.2.840.10045.4.1":
					case "1.2.840.113549.1.1.5":
						result = "SHA-1";
						break;
					case "1.2.840.10045.4.3.2":
					case "1.2.840.113549.1.1.11":
						result = "SHA-256";
						break;
					case "1.2.840.10045.4.3.3":
					case "1.2.840.113549.1.1.12":
						result = "SHA-384";
						break;
					case "1.2.840.10045.4.3.4":
					case "1.2.840.113549.1.1.13":
						result = "SHA-512";
						break;
					case "1.2.840.113549.1.1.10":
						{
							try {
								var params = new RSASSAPSSParams({ schema: signatureAlgorithm.algorithmParams });
								if ("hashAlgorithm" in params) {
									var algorithm = this.getAlgorithmByOID(params.hashAlgorithm.algorithmId);
									if ("name" in algorithm === false) return "";

									result = algorithm.name;
								} else result = "SHA-1";
							} catch (ex) {}
						}
						break;
					default:
				}

				return result;
			}
		}, {
			key: 'encryptEncryptedContentInfo',
			value: function encryptEncryptedContentInfo(parameters) {
				var _this56 = this;

				if (parameters instanceof Object === false) return Promise.reject("Parameters must have type \"Object\"");

				if ("password" in parameters === false) return Promise.reject("Absent mandatory parameter \"password\"");

				if ("contentEncryptionAlgorithm" in parameters === false) return Promise.reject("Absent mandatory parameter \"contentEncryptionAlgorithm\"");

				if ("hmacHashAlgorithm" in parameters === false) return Promise.reject("Absent mandatory parameter \"hmacHashAlgorithm\"");

				if ("iterationCount" in parameters === false) return Promise.reject("Absent mandatory parameter \"iterationCount\"");

				if ("contentToEncrypt" in parameters === false) return Promise.reject("Absent mandatory parameter \"contentToEncrypt\"");

				if ("contentType" in parameters === false) return Promise.reject("Absent mandatory parameter \"contentType\"");

				var contentEncryptionOID = this.getOIDByAlgorithm(parameters.contentEncryptionAlgorithm);
				if (contentEncryptionOID === "") return Promise.reject("Wrong \"contentEncryptionAlgorithm\" value");

				var pbkdf2OID = this.getOIDByAlgorithm({
					name: "PBKDF2"
				});
				if (pbkdf2OID === "") return Promise.reject("Can not find OID for PBKDF2");

				var hmacOID = this.getOIDByAlgorithm({
					name: "HMAC",
					hash: {
						name: parameters.hmacHashAlgorithm
					}
				});
				if (hmacOID === "") return Promise.reject('Incorrect value for "hmacHashAlgorithm": ' + parameters.hmacHashAlgorithm);

				var sequence = Promise.resolve();

				var ivBuffer = new ArrayBuffer(16);
				var ivView = new Uint8Array(ivBuffer);
				this.getRandomValues(ivView);

				var saltBuffer = new ArrayBuffer(64);
				var saltView = new Uint8Array(saltBuffer);
				this.getRandomValues(saltView);

				var contentView = new Uint8Array(parameters.contentToEncrypt);

				var pbkdf2Params = new PBKDF2Params({
					salt: new OctetString({ valueHex: saltBuffer }),
					iterationCount: parameters.iterationCount,
					prf: new AlgorithmIdentifier({
						algorithmId: hmacOID,
						algorithmParams: new Null()
					})
				});

				sequence = sequence.then(function () {
					var passwordView = new Uint8Array(parameters.password);

					return _this56.importKey("raw", passwordView, "PBKDF2", false, ["deriveKey"]);
				}, function (error) {
					return Promise.reject(error);
				});

				sequence = sequence.then(function (result) {
					return _this56.deriveKey({
						name: "PBKDF2",
						hash: {
							name: parameters.hmacHashAlgorithm
						},
						salt: saltView,
						iterations: parameters.iterationCount
					}, result, parameters.contentEncryptionAlgorithm, false, ["encrypt"]);
				}, function (error) {
					return Promise.reject(error);
				});

				sequence = sequence.then(function (result) {
					return _this56.encrypt({
						name: parameters.contentEncryptionAlgorithm.name,
						iv: ivView
					}, result, contentView);
				}, function (error) {
					return Promise.reject(error);
				});

				sequence = sequence.then(function (result) {
					var pbes2Parameters = new PBES2Params({
						keyDerivationFunc: new AlgorithmIdentifier({
							algorithmId: pbkdf2OID,
							algorithmParams: pbkdf2Params.toSchema()
						}),
						encryptionScheme: new AlgorithmIdentifier({
							algorithmId: contentEncryptionOID,
							algorithmParams: new OctetString({ valueHex: ivBuffer })
						})
					});

					return new EncryptedContentInfo({
						contentType: parameters.contentType,
						contentEncryptionAlgorithm: new AlgorithmIdentifier({
							algorithmId: "1.2.840.113549.1.5.13",
							algorithmParams: pbes2Parameters.toSchema()
						}),
						encryptedContent: new OctetString({ valueHex: result })
					});
				}, function (error) {
					return Promise.reject(error);
				});


				return sequence;
			}
		}, {
			key: 'decryptEncryptedContentInfo',
			value: function decryptEncryptedContentInfo(parameters) {
				var _this57 = this;

				if (parameters instanceof Object === false) return Promise.reject("Parameters must have type \"Object\"");

				if ("password" in parameters === false) return Promise.reject("Absent mandatory parameter \"password\"");

				if ("encryptedContentInfo" in parameters === false) return Promise.reject("Absent mandatory parameter \"encryptedContentInfo\"");

				if (parameters.encryptedContentInfo.contentEncryptionAlgorithm.algorithmId !== "1.2.840.113549.1.5.13") return Promise.reject('Unknown "contentEncryptionAlgorithm": ' + this.encryptedContentInfo.contentEncryptionAlgorithm.algorithmId);

				var sequence = Promise.resolve();

				var pbes2Parameters = void 0;

				try {
					pbes2Parameters = new PBES2Params({ schema: parameters.encryptedContentInfo.contentEncryptionAlgorithm.algorithmParams });
				} catch (ex) {
					return Promise.reject("Incorrectly encoded \"pbes2Parameters\"");
				}

				var pbkdf2Params = void 0;

				try {
					pbkdf2Params = new PBKDF2Params({ schema: pbes2Parameters.keyDerivationFunc.algorithmParams });
				} catch (ex) {
					return Promise.reject("Incorrectly encoded \"pbkdf2Params\"");
				}

				var contentEncryptionAlgorithm = this.getAlgorithmByOID(pbes2Parameters.encryptionScheme.algorithmId);
				if ("name" in contentEncryptionAlgorithm === false) return Promise.reject('Incorrect OID for "contentEncryptionAlgorithm": ' + pbes2Parameters.encryptionScheme.algorithmId);

				var ivBuffer = pbes2Parameters.encryptionScheme.algorithmParams.valueBlock.valueHex;
				var ivView = new Uint8Array(ivBuffer);

				var saltBuffer = pbkdf2Params.salt.valueBlock.valueHex;
				var saltView = new Uint8Array(saltBuffer);

				var iterationCount = pbkdf2Params.iterationCount;

				var hmacHashAlgorithm = "SHA-1";

				if ("prf" in pbkdf2Params) {
					var algorithm = this.getAlgorithmByOID(pbkdf2Params.prf.algorithmId);
					if ("name" in algorithm === false) return Promise.reject("Incorrect OID for HMAC hash algorithm");

					hmacHashAlgorithm = algorithm.hash.name;
				}

				sequence = sequence.then(function () {
					return _this57.importKey("raw", parameters.password, "PBKDF2", false, ["deriveKey"]);
				}, function (error) {
					return Promise.reject(error);
				});

				sequence = sequence.then(function (result) {
					return _this57.deriveKey({
						name: "PBKDF2",
						hash: {
							name: hmacHashAlgorithm
						},
						salt: saltView,
						iterations: iterationCount
					}, result, contentEncryptionAlgorithm, false, ["decrypt"]);
				}, function (error) {
					return Promise.reject(error);
				});

				sequence = sequence.then(function (result) {
					var dataBuffer = new ArrayBuffer(0);

					if (parameters.encryptedContentInfo.encryptedContent.idBlock.isConstructed === false) dataBuffer = parameters.encryptedContentInfo.encryptedContent.valueBlock.valueHex;else {
						var _iteratorNormalCompletion19 = true;
						var _didIteratorError19 = false;
						var _iteratorError19 = undefined;

						try {
							for (var _iterator19 = parameters.encryptedContentInfo.encryptedContent.valueBlock.value[Symbol.iterator](), _step19; !(_iteratorNormalCompletion19 = (_step19 = _iterator19.next()).done); _iteratorNormalCompletion19 = true) {
								var content = _step19.value;

								dataBuffer = utilConcatBuf$1(dataBuffer, content.valueBlock.valueHex);
							}
						} catch (err) {
							_didIteratorError19 = true;
							_iteratorError19 = err;
						} finally {
							try {
								if (!_iteratorNormalCompletion19 && _iterator19.return) {
									_iterator19.return();
								}
							} finally {
								if (_didIteratorError19) {
									throw _iteratorError19;
								}
							}
						}
					}


					return _this57.decrypt({
						name: contentEncryptionAlgorithm.name,
						iv: ivView
					}, result, dataBuffer);
				}, function (error) {
					return Promise.reject(error);
				});


				return sequence;
			}
		}, {
			key: 'stampDataWithPassword',
			value: function stampDataWithPassword(parameters) {
				var _this58 = this;

				if (parameters instanceof Object === false) return Promise.reject("Parameters must have type \"Object\"");

				if ("password" in parameters === false) return Promise.reject("Absent mandatory parameter \"password\"");

				if ("hashAlgorithm" in parameters === false) return Promise.reject("Absent mandatory parameter \"hashAlgorithm\"");

				if ("salt" in parameters === false) return Promise.reject("Absent mandatory parameter \"iterationCount\"");

				if ("iterationCount" in parameters === false) return Promise.reject("Absent mandatory parameter \"salt\"");

				if ("contentToStamp" in parameters === false) return Promise.reject("Absent mandatory parameter \"contentToStamp\"");

				var length = void 0;

				switch (parameters.hashAlgorithm.toLowerCase()) {
					case "sha-1":
						length = 160;
						break;
					case "sha-256":
						length = 256;
						break;
					case "sha-384":
						length = 384;
						break;
					case "sha-512":
						length = 512;
						break;
					default:
						return Promise.reject('Incorrect "parameters.hashAlgorithm" parameter: ' + parameters.hashAlgorithm);
				}

				var sequence = Promise.resolve();

				var hmacAlgorithm = {
					name: "HMAC",
					length: length,
					hash: {
						name: parameters.hashAlgorithm
					}
				};

				sequence = sequence.then(function () {
					return makePKCS12B2Key(_this58, parameters.hashAlgorithm, length, parameters.password, parameters.salt, parameters.iterationCount);
				});

				sequence = sequence.then(function (result) {
					return _this58.importKey("raw", new Uint8Array(result), hmacAlgorithm, false, ["sign"]);
				});

				sequence = sequence.then(function (result) {
					return _this58.sign(hmacAlgorithm, result, new Uint8Array(parameters.contentToStamp));
				}, function (error) {
					return Promise.reject(error);
				});


				return sequence;
			}
		}, {
			key: 'verifyDataStampedWithPassword',
			value: function verifyDataStampedWithPassword(parameters) {
				var _this59 = this;

				if (parameters instanceof Object === false) return Promise.reject("Parameters must have type \"Object\"");

				if ("password" in parameters === false) return Promise.reject("Absent mandatory parameter \"password\"");

				if ("hashAlgorithm" in parameters === false) return Promise.reject("Absent mandatory parameter \"hashAlgorithm\"");

				if ("salt" in parameters === false) return Promise.reject("Absent mandatory parameter \"iterationCount\"");

				if ("iterationCount" in parameters === false) return Promise.reject("Absent mandatory parameter \"salt\"");

				if ("contentToVerify" in parameters === false) return Promise.reject("Absent mandatory parameter \"contentToVerify\"");

				if ("signatureToVerify" in parameters === false) return Promise.reject("Absent mandatory parameter \"signatureToVerify\"");

				var length = void 0;

				switch (parameters.hashAlgorithm.toLowerCase()) {
					case "sha-1":
						length = 160;
						break;
					case "sha-256":
						length = 256;
						break;
					case "sha-384":
						length = 384;
						break;
					case "sha-512":
						length = 512;
						break;
					default:
						return Promise.reject('Incorrect "parameters.hashAlgorithm" parameter: ' + parameters.hashAlgorithm);
				}

				var sequence = Promise.resolve();

				var hmacAlgorithm = {
					name: "HMAC",
					length: length,
					hash: {
						name: parameters.hashAlgorithm
					}
				};

				sequence = sequence.then(function () {
					return makePKCS12B2Key(_this59, parameters.hashAlgorithm, length, parameters.password, parameters.salt, parameters.iterationCount);
				});

				sequence = sequence.then(function (result) {
					return _this59.importKey("raw", new Uint8Array(result), hmacAlgorithm, false, ["verify"]);
				});

				sequence = sequence.then(function (result) {
					return _this59.verify(hmacAlgorithm, result, new Uint8Array(parameters.signatureToVerify), new Uint8Array(parameters.contentToVerify));
				}, function (error) {
					return Promise.reject(error);
				});


				return sequence;
			}
		}, {
			key: 'getSignatureParameters',
			value: function getSignatureParameters(privateKey) {
				var hashAlgorithm = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : "SHA-1";

				var oid = this.getOIDByAlgorithm({ name: hashAlgorithm });
				if (oid === "") return Promise.reject('Unsupported hash algorithm: ' + hashAlgorithm);

				var signatureAlgorithm = new AlgorithmIdentifier();

				var parameters = this.getAlgorithmParameters(privateKey.algorithm.name, "sign");
				parameters.algorithm.hash.name = hashAlgorithm;

				switch (privateKey.algorithm.name.toUpperCase()) {
					case "RSASSA-PKCS1-V1_5":
					case "ECDSA":
						signatureAlgorithm.algorithmId = this.getOIDByAlgorithm(parameters.algorithm);
						break;
					case "RSA-PSS":
						{
							switch (hashAlgorithm.toUpperCase()) {
								case "SHA-256":
									parameters.algorithm.saltLength = 32;
									break;
								case "SHA-384":
									parameters.algorithm.saltLength = 48;
									break;
								case "SHA-512":
									parameters.algorithm.saltLength = 64;
									break;
								default:
							}

							var paramsObject = {};

							if (hashAlgorithm.toUpperCase() !== "SHA-1") {
								var hashAlgorithmOID = this.getOIDByAlgorithm({ name: hashAlgorithm });
								if (hashAlgorithmOID === "") return Promise.reject('Unsupported hash algorithm: ' + hashAlgorithm);

								paramsObject.hashAlgorithm = new AlgorithmIdentifier({
									algorithmId: hashAlgorithmOID,
									algorithmParams: new Null()
								});

								paramsObject.maskGenAlgorithm = new AlgorithmIdentifier({
									algorithmId: "1.2.840.113549.1.1.8",
									algorithmParams: paramsObject.hashAlgorithm.toSchema()
								});
							}

							if (parameters.algorithm.saltLength !== 20) paramsObject.saltLength = parameters.algorithm.saltLength;

							var pssParameters = new RSASSAPSSParams(paramsObject);

							signatureAlgorithm.algorithmId = "1.2.840.113549.1.1.10";
							signatureAlgorithm.algorithmParams = pssParameters.toSchema();
						}
						break;
					default:
						return Promise.reject('Unsupported signature algorithm: ' + privateKey.algorithm.name);
				}


				return Promise.resolve().then(function () {
					return {
						signatureAlgorithm: signatureAlgorithm,
						parameters: parameters
					};
				});
			}
		}, {
			key: 'signWithPrivateKey',
			value: function signWithPrivateKey(data, privateKey, parameters) {
				return this.sign(parameters.algorithm, privateKey, new Uint8Array(data)).then(function (result) {
					if (parameters.algorithm.name === "ECDSA") result = createCMSECDSASignature(result);


					return result;
				}, function (error) {
					return Promise.reject('Signing error: ' + error);
				});
			}
		}]);

		return CryptoEngine;
	}();

	var engine = {
		name: "none",
		crypto: null,
		subtle: null
	};

	function setEngine(name, crypto, subtle) {
		engine = {
			name: name,
			crypto: crypto,
			subtle: subtle
		};
	}

	function getEngine() {
		return engine;
	}

	(function initCryptoEngine() {
		if (typeof self !== "undefined") {
			if ("crypto" in self) {
				var engineName = "webcrypto";

				var cryptoObject = self.crypto;
				var subtleObject = null;

				if ("webkitSubtle" in self.crypto) {
					try {
						subtleObject = self.crypto.webkitSubtle;
					} catch (ex) {
						subtleObject = self.crypto.subtle;
					}

					engineName = "safari";
				}

				if ("subtle" in self.crypto) subtleObject = self.crypto.subtle;

				engine = {
					name: engineName,
					crypto: cryptoObject,
					subtle: new CryptoEngine({ name: engineName, crypto: self.crypto, subtle: subtleObject })
				};
			}
		}
	})();

	function getCrypto() {
		if (engine.subtle !== null) return engine.subtle;

		return undefined;
	}

	function getAlgorithmParameters(algorithmName, operation) {
		return engine.subtle.getAlgorithmParameters(algorithmName, operation);
	}

	function createCMSECDSASignature(signatureBuffer) {
		if (signatureBuffer.byteLength % 2 !== 0) return new ArrayBuffer(0);

		var length = signatureBuffer.byteLength / 2;

		var rBuffer = new ArrayBuffer(length);
		var rView = new Uint8Array(rBuffer);
		rView.set(new Uint8Array(signatureBuffer, 0, length));

		var rInteger = new Integer({ valueHex: rBuffer });

		var sBuffer = new ArrayBuffer(length);
		var sView = new Uint8Array(sBuffer);
		sView.set(new Uint8Array(signatureBuffer, length, length));

		var sInteger = new Integer({ valueHex: sBuffer });


		return new Sequence({
			value: [rInteger.convertToDER(), sInteger.convertToDER()]
		}).toBER(false);
	}

	function stringPrep(inputString) {
		var result = inputString.replace(/^\s+|\s+$/g, "");
		result = result.replace(/\s+/g, " ");
		result = result.toLowerCase();

		return result;
	}

	function createECDSASignatureFromCMS(cmsSignature) {
		if (cmsSignature instanceof Sequence === false) return new ArrayBuffer(0);

		if (cmsSignature.valueBlock.value.length !== 2) return new ArrayBuffer(0);

		if (cmsSignature.valueBlock.value[0] instanceof Integer === false) return new ArrayBuffer(0);

		if (cmsSignature.valueBlock.value[1] instanceof Integer === false) return new ArrayBuffer(0);


		var rValue = cmsSignature.valueBlock.value[0].convertFromDER();
		var sValue = cmsSignature.valueBlock.value[1].convertFromDER();

		switch (true) {
			case rValue.valueBlock.valueHex.byteLength < sValue.valueBlock.valueHex.byteLength:
				{
					if (sValue.valueBlock.valueHex.byteLength - rValue.valueBlock.valueHex.byteLength !== 1) throw new Error("Incorrect DER integer decoding");

					var correctedLength = sValue.valueBlock.valueHex.byteLength;

					var rValueView = new Uint8Array(rValue.valueBlock.valueHex);

					var rValueBufferCorrected = new ArrayBuffer(correctedLength);
					var rValueViewCorrected = new Uint8Array(rValueBufferCorrected);

					rValueViewCorrected.set(rValueView, 1);
					rValueViewCorrected[0] = 0x00;

					return utilConcatBuf$1(rValueBufferCorrected, sValue.valueBlock.valueHex);
				}
			case rValue.valueBlock.valueHex.byteLength > sValue.valueBlock.valueHex.byteLength:
				{
					if (rValue.valueBlock.valueHex.byteLength - sValue.valueBlock.valueHex.byteLength !== 1) throw new Error("Incorrect DER integer decoding");

					var _correctedLength = rValue.valueBlock.valueHex.byteLength;

					var sValueView = new Uint8Array(sValue.valueBlock.valueHex);

					var sValueBufferCorrected = new ArrayBuffer(_correctedLength);
					var sValueViewCorrected = new Uint8Array(sValueBufferCorrected);

					sValueViewCorrected.set(sValueView, 1);
					sValueViewCorrected[0] = 0x00;

					return utilConcatBuf$1(rValue.valueBlock.valueHex, sValueBufferCorrected);
				}
			default:
				{
					if (rValue.valueBlock.valueHex.byteLength % 2) {
						var _correctedLength2 = rValue.valueBlock.valueHex.byteLength + 1;

						var _rValueView = new Uint8Array(rValue.valueBlock.valueHex);

						var _rValueBufferCorrected = new ArrayBuffer(_correctedLength2);
						var _rValueViewCorrected = new Uint8Array(_rValueBufferCorrected);

						_rValueViewCorrected.set(_rValueView, 1);
						_rValueViewCorrected[0] = 0x00;

						var _sValueView = new Uint8Array(sValue.valueBlock.valueHex);

						var _sValueBufferCorrected = new ArrayBuffer(_correctedLength2);
						var _sValueViewCorrected = new Uint8Array(_sValueBufferCorrected);

						_sValueViewCorrected.set(_sValueView, 1);
						_sValueViewCorrected[0] = 0x00;

						return utilConcatBuf$1(_rValueBufferCorrected, _sValueBufferCorrected);
					}
				}
		}


		return utilConcatBuf$1(rValue.valueBlock.valueHex, sValue.valueBlock.valueHex);
	}

	function getAlgorithmByOID(oid) {
		return engine.subtle.getAlgorithmByOID(oid);
	}

	function getHashAlgorithm(signatureAlgorithm) {
		return engine.subtle.getHashAlgorithm(signatureAlgorithm);
	}

	var AttributeTypeAndValue = function () {
		function AttributeTypeAndValue() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, AttributeTypeAndValue);

			this.type = getParametersValue$1(parameters, "type", AttributeTypeAndValue.defaultValues("type"));

			this.value = getParametersValue$1(parameters, "value", AttributeTypeAndValue.defaultValues("value"));

			if ("schema" in parameters) this.fromSchema(parameters.schema);
		}

		_createClass(AttributeTypeAndValue, [{
			key: 'fromSchema',
			value: function fromSchema(schema) {
				var asn1 = compareSchema(schema, schema, AttributeTypeAndValue.schema({
					names: {
						type: "type",
						value: "typeValue"
					}
				}));

				if (asn1.verified === false) throw new Error("Object's schema was not verified against input data for ATTR_TYPE_AND_VALUE");

				this.type = asn1.result.type.valueBlock.toString();
				this.value = asn1.result.typeValue;
			}
		}, {
			key: 'toSchema',
			value: function toSchema() {
				return new Sequence({
					value: [new ObjectIdentifier$1({ value: this.type }), this.value]
				});
			}
		}, {
			key: 'toJSON',
			value: function toJSON() {
				var _object = {
					type: this.type
				};

				if (Object.keys(this.value).length !== 0) _object.value = this.value.toJSON();else _object.value = this.value;

				return _object;
			}
		}, {
			key: 'isEqual',
			value: function isEqual(compareTo) {
				if (compareTo instanceof AttributeTypeAndValue) {
					if (this.type !== compareTo.type) return false;

					if (this.value instanceof Utf8String && compareTo.value instanceof Utf8String || this.value instanceof BmpString && compareTo.value instanceof BmpString || this.value instanceof UniversalString && compareTo.value instanceof UniversalString || this.value instanceof NumericString && compareTo.value instanceof NumericString || this.value instanceof PrintableString && compareTo.value instanceof PrintableString || this.value instanceof TeletexString && compareTo.value instanceof TeletexString || this.value instanceof VideotexString && compareTo.value instanceof VideotexString || this.value instanceof IA5String && compareTo.value instanceof IA5String || this.value instanceof GraphicString && compareTo.value instanceof GraphicString || this.value instanceof VisibleString && compareTo.value instanceof VisibleString || this.value instanceof GeneralString && compareTo.value instanceof GeneralString || this.value instanceof CharacterString && compareTo.value instanceof CharacterString) {
						var value1 = stringPrep(this.value.valueBlock.value);
						var value2 = stringPrep(compareTo.value.valueBlock.value);

						if (value1.localeCompare(value2) !== 0) return false;
					} else {
							if (isEqualBuffer$1(this.value.valueBeforeDecode, compareTo.value.valueBeforeDecode) === false) return false;
						}

					return true;
				}

				if (compareTo instanceof ArrayBuffer) return isEqualBuffer$1(this.value.valueBeforeDecode, compareTo);

				return false;
			}
		}], [{
			key: 'defaultValues',
			value: function defaultValues(memberName) {
				switch (memberName) {
					case "type":
						return "";
					case "value":
						return {};
					default:
						throw new Error('Invalid member name for AttributeTypeAndValue class: ' + memberName);
				}
			}
		}, {
			key: 'schema',
			value: function schema() {
				var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

				var names = getParametersValue$1(parameters, "names", {});

				return new Sequence({
					name: names.blockName || "",
					value: [new ObjectIdentifier$1({ name: names.type || "" }), new Any$1({ name: names.value || "" })]
				});
			}
		}]);

		return AttributeTypeAndValue;
	}();

	var RelativeDistinguishedNames = function () {
		function RelativeDistinguishedNames() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, RelativeDistinguishedNames);

			this.typesAndValues = getParametersValue$1(parameters, "typesAndValues", RelativeDistinguishedNames.defaultValues("typesAndValues"));

			this.valueBeforeDecode = getParametersValue$1(parameters, "valueBeforeDecode", RelativeDistinguishedNames.defaultValues("valueBeforeDecode"));

			if ("schema" in parameters) this.fromSchema(parameters.schema);
		}

		_createClass(RelativeDistinguishedNames, [{
			key: 'fromSchema',
			value: function fromSchema(schema) {
				var asn1 = compareSchema(schema, schema, RelativeDistinguishedNames.schema({
					names: {
						blockName: "RDN",
						repeatedSet: "typesAndValues"
					}
				}));

				if (asn1.verified === false) throw new Error("Object's schema was not verified against input data for RDN");

				if ("typesAndValues" in asn1.result) this.typesAndValues = Array.from(asn1.result.typesAndValues, function (element) {
						return new AttributeTypeAndValue({ schema: element });
					});

				this.valueBeforeDecode = asn1.result.RDN.valueBeforeDecode;
			}
		}, {
			key: 'toSchema',
			value: function toSchema() {
				if (this.valueBeforeDecode.byteLength === 0) {
						return new Sequence({
							value: [new Set({
								value: Array.from(this.typesAndValues, function (element) {
									return element.toSchema();
								})
							})]
						});
					}

				var asn1 = fromBER(this.valueBeforeDecode);

				return asn1.result;
			}
		}, {
			key: 'toJSON',
			value: function toJSON() {
				return {
					typesAndValues: Array.from(this.typesAndValues, function (element) {
						return element.toJSON();
					})
				};
			}
		}, {
			key: 'isEqual',
			value: function isEqual(compareTo) {
				if (compareTo instanceof RelativeDistinguishedNames) {
					if (this.typesAndValues.length !== compareTo.typesAndValues.length) return false;

					var _iteratorNormalCompletion20 = true;
					var _didIteratorError20 = false;
					var _iteratorError20 = undefined;

					try {
						for (var _iterator20 = this.typesAndValues.entries()[Symbol.iterator](), _step20; !(_iteratorNormalCompletion20 = (_step20 = _iterator20.next()).done); _iteratorNormalCompletion20 = true) {
							var _step20$value = _slicedToArray(_step20.value, 2),
							    index = _step20$value[0],
							    typeAndValue = _step20$value[1];

							if (typeAndValue.isEqual(compareTo.typesAndValues[index]) === false) return false;
						}
					} catch (err) {
						_didIteratorError20 = true;
						_iteratorError20 = err;
					} finally {
						try {
							if (!_iteratorNormalCompletion20 && _iterator20.return) {
								_iterator20.return();
							}
						} finally {
							if (_didIteratorError20) {
								throw _iteratorError20;
							}
						}
					}

					return true;
				}

				if (compareTo instanceof ArrayBuffer) return isEqualBuffer$1(this.valueBeforeDecode, compareTo);

				return false;
			}
		}], [{
			key: 'defaultValues',
			value: function defaultValues(memberName) {
				switch (memberName) {
					case "typesAndValues":
						return [];
					case "valueBeforeDecode":
						return new ArrayBuffer(0);
					default:
						throw new Error('Invalid member name for RelativeDistinguishedNames class: ' + memberName);
				}
			}
		}, {
			key: 'compareWithDefault',
			value: function compareWithDefault(memberName, memberValue) {
				switch (memberName) {
					case "typesAndValues":
						return memberValue.length === 0;
					case "valueBeforeDecode":
						return memberValue.byteLength === 0;
					default:
						throw new Error('Invalid member name for RelativeDistinguishedNames class: ' + memberName);
				}
			}
		}, {
			key: 'schema',
			value: function schema() {
				var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

				var names = getParametersValue$1(parameters, "names", {});

				return new Sequence({
					name: names.blockName || "",
					value: [new Repeated({
						name: names.repeatedSequence || "",
						value: new Set({
							value: [new Repeated({
								name: names.repeatedSet || "",
								value: AttributeTypeAndValue.schema(names.typeAndValue || {})
							})]
						})
					})]
				});
			}
		}]);

		return RelativeDistinguishedNames;
	}();

	function builtInStandardAttributes() {
		var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};
		var optional = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : false;

		var names = getParametersValue$1(parameters, "names", {});

		return new Sequence({
			optional: optional,
			value: [new Constructed({
				optional: true,
				idBlock: {
					tagClass: 2,
					tagNumber: 1 },
				name: names.country_name || "",
				value: [new Choice({
					value: [new NumericString(), new PrintableString()]
				})]
			}), new Constructed({
				optional: true,
				idBlock: {
					tagClass: 2,
					tagNumber: 2 },
				name: names.administration_domain_name || "",
				value: [new Choice({
					value: [new NumericString(), new PrintableString()]
				})]
			}), new Primitive({
				optional: true,
				idBlock: {
					tagClass: 3,
					tagNumber: 0 },
				name: names.network_address || "",
				isHexOnly: true
			}), new Primitive({
				optional: true,
				idBlock: {
					tagClass: 3,
					tagNumber: 1 },
				name: names.terminal_identifier || "",
				isHexOnly: true
			}), new Constructed({
				optional: true,
				idBlock: {
					tagClass: 3,
					tagNumber: 2 },
				name: names.private_domain_name || "",
				value: [new Choice({
					value: [new NumericString(), new PrintableString()]
				})]
			}), new Primitive({
				optional: true,
				idBlock: {
					tagClass: 3,
					tagNumber: 3 },
				name: names.organization_name || "",
				isHexOnly: true
			}), new Primitive({
				optional: true,
				name: names.numeric_user_identifier || "",
				idBlock: {
					tagClass: 3,
					tagNumber: 4 },
				isHexOnly: true
			}), new Constructed({
				optional: true,
				name: names.personal_name || "",
				idBlock: {
					tagClass: 3,
					tagNumber: 5 },
				value: [new Primitive({
					idBlock: {
						tagClass: 3,
						tagNumber: 0 },
					isHexOnly: true
				}), new Primitive({
					optional: true,
					idBlock: {
						tagClass: 3,
						tagNumber: 1 },
					isHexOnly: true
				}), new Primitive({
					optional: true,
					idBlock: {
						tagClass: 3,
						tagNumber: 2 },
					isHexOnly: true
				}), new Primitive({
					optional: true,
					idBlock: {
						tagClass: 3,
						tagNumber: 3 },
					isHexOnly: true
				})]
			}), new Constructed({
				optional: true,
				name: names.organizational_unit_names || "",
				idBlock: {
					tagClass: 3,
					tagNumber: 6 },
				value: [new Repeated({
					value: new PrintableString()
				})]
			})]
		});
	}

	function builtInDomainDefinedAttributes() {
		var optional = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : false;

		return new Sequence({
			optional: optional,
			value: [new PrintableString(), new PrintableString()]
		});
	}

	function extensionAttributes() {
		var optional = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : false;

		return new Set({
			optional: optional,
			value: [new Primitive({
				optional: true,
				idBlock: {
					tagClass: 3,
					tagNumber: 0 },
				isHexOnly: true
			}), new Constructed({
				optional: true,
				idBlock: {
					tagClass: 3,
					tagNumber: 1 },
				value: [new Any$1()]
			})]
		});
	}

	var GeneralName = function () {
		function GeneralName() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, GeneralName);

			this.type = getParametersValue$1(parameters, "type", GeneralName.defaultValues("type"));

			this.value = getParametersValue$1(parameters, "value", GeneralName.defaultValues("value"));

			if ("schema" in parameters) this.fromSchema(parameters.schema);
		}

		_createClass(GeneralName, [{
			key: 'fromSchema',
			value: function fromSchema(schema) {
				var asn1 = compareSchema(schema, schema, GeneralName.schema({
					names: {
						blockName: "blockName",
						otherName: "otherName",
						rfc822Name: "rfc822Name",
						dNSName: "dNSName",
						x400Address: "x400Address",
						directoryName: {
							names: {
								blockName: "directoryName"
							}
						},
						ediPartyName: "ediPartyName",
						uniformResourceIdentifier: "uniformResourceIdentifier",
						iPAddress: "iPAddress",
						registeredID: "registeredID"
					}
				}));

				if (asn1.verified === false) throw new Error("Object's schema was not verified against input data for GENERAL_NAME");

				this.type = asn1.result.blockName.idBlock.tagNumber;

				switch (this.type) {
					case 0:
						this.value = asn1.result.blockName;
						break;
					case 1:
					case 2:
					case 6:
						{
							var value = asn1.result.blockName;

							value.idBlock.tagClass = 1;
							value.idBlock.tagNumber = 22;

							var valueBER = value.toBER(false);

							this.value = fromBER(valueBER).result.valueBlock.value;
						}
						break;
					case 3:
						this.value = asn1.result.blockName;
						break;
					case 4:
						this.value = new RelativeDistinguishedNames({ schema: asn1.result.directoryName });
						break;
					case 5:
						this.value = asn1.result.ediPartyName;
						break;
					case 7:
						this.value = new OctetString({ valueHex: asn1.result.blockName.valueBlock.valueHex });
						break;
					case 8:
						{
							var _value7 = asn1.result.blockName;

							_value7.idBlock.tagClass = 1;
							_value7.idBlock.tagNumber = 6;

							var _valueBER = _value7.toBER(false);

							this.value = fromBER(_valueBER).result.valueBlock.toString();
						}
						break;
					default:
				}
			}
		}, {
			key: 'toSchema',
			value: function toSchema() {
				switch (this.type) {
					case 0:
					case 3:
					case 5:
						return new Constructed({
							idBlock: {
								tagClass: 3,
								tagNumber: this.type
							},
							value: [this.value]
						});
					case 1:
					case 2:
					case 6:
						{
							var value = new IA5String({ value: this.value });

							value.idBlock.tagClass = 3;
							value.idBlock.tagNumber = this.type;

							return value;
						}
					case 4:
						return new Constructed({
							idBlock: {
								tagClass: 3,
								tagNumber: 4
							},
							value: [this.value.toSchema()]
						});
					case 7:
						{
							var _value8 = this.value;

							_value8.idBlock.tagClass = 3;
							_value8.idBlock.tagNumber = this.type;

							return _value8;
						}
					case 8:
						{
							var _value9 = new ObjectIdentifier$1({ value: this.value });

							_value9.idBlock.tagClass = 3;
							_value9.idBlock.tagNumber = this.type;

							return _value9;
						}
					default:
						return GeneralName.schema();
				}
			}
		}, {
			key: 'toJSON',
			value: function toJSON() {
				var _object = {
					type: this.type
				};

				if (typeof this.value === "string") _object.value = this.value;else _object.value = this.value.toJSON();

				return _object;
			}
		}], [{
			key: 'defaultValues',
			value: function defaultValues(memberName) {
				switch (memberName) {
					case "type":
						return 9;
					case "value":
						return {};
					default:
						throw new Error('Invalid member name for GeneralName class: ' + memberName);
				}
			}
		}, {
			key: 'compareWithDefault',
			value: function compareWithDefault(memberName, memberValue) {
				switch (memberName) {
					case "type":
						return memberValue === GeneralName.defaultValues(memberName);
					case "value":
						return Object.keys(memberValue).length === 0;
					default:
						throw new Error('Invalid member name for GeneralName class: ' + memberName);
				}
			}
		}, {
			key: 'schema',
			value: function schema() {
				var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

				var names = getParametersValue$1(parameters, "names", {});

				return new Choice({
					value: [new Constructed({
						idBlock: {
							tagClass: 3,
							tagNumber: 0 },
						name: names.blockName || "",
						value: [new ObjectIdentifier$1(), new Constructed({
							idBlock: {
								tagClass: 3,
								tagNumber: 0 },
							value: [new Any$1()]
						})]
					}), new Primitive({
						name: names.blockName || "",
						idBlock: {
							tagClass: 3,
							tagNumber: 1 }
					}), new Primitive({
						name: names.blockName || "",
						idBlock: {
							tagClass: 3,
							tagNumber: 2 }
					}), new Constructed({
						idBlock: {
							tagClass: 3,
							tagNumber: 3 },
						name: names.blockName || "",
						value: [builtInStandardAttributes(names.builtInStandardAttributes || {}, false), builtInDomainDefinedAttributes(true), extensionAttributes(true)]
					}), new Constructed({
						idBlock: {
							tagClass: 3,
							tagNumber: 4 },
						name: names.blockName || "",
						value: [RelativeDistinguishedNames.schema(names.directoryName || {})]
					}), new Constructed({
						idBlock: {
							tagClass: 3,
							tagNumber: 5 },
						name: names.blockName || "",
						value: [new Constructed({
							optional: true,
							idBlock: {
								tagClass: 3,
								tagNumber: 0 },
							value: [new Choice({
								value: [new TeletexString(), new PrintableString(), new UniversalString(), new Utf8String(), new BmpString()]
							})]
						}), new Constructed({
							idBlock: {
								tagClass: 3,
								tagNumber: 1 },
							value: [new Choice({
								value: [new TeletexString(), new PrintableString(), new UniversalString(), new Utf8String(), new BmpString()]
							})]
						})]
					}), new Primitive({
						name: names.blockName || "",
						idBlock: {
							tagClass: 3,
							tagNumber: 6 }
					}), new Primitive({
						name: names.blockName || "",
						idBlock: {
							tagClass: 3,
							tagNumber: 7 }
					}), new Primitive({
						name: names.blockName || "",
						idBlock: {
							tagClass: 3,
							tagNumber: 8 }
					})]
				});
			}
		}]);

		return GeneralName;
	}();

	var AccessDescription = function () {
		function AccessDescription() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, AccessDescription);

			this.accessMethod = getParametersValue$1(parameters, "accessMethod", AccessDescription.defaultValues("accessMethod"));

			this.accessLocation = getParametersValue$1(parameters, "accessLocation", AccessDescription.defaultValues("accessLocation"));

			if ("schema" in parameters) this.fromSchema(parameters.schema);
		}

		_createClass(AccessDescription, [{
			key: 'fromSchema',
			value: function fromSchema(schema) {
				var asn1 = compareSchema(schema, schema, AccessDescription.schema({
					names: {
						accessMethod: "accessMethod",
						accessLocation: {
							names: {
								blockName: "accessLocation"
							}
						}
					}
				}));

				if (asn1.verified === false) throw new Error("Object's schema was not verified against input data for AccessDescription");

				this.accessMethod = asn1.result.accessMethod.valueBlock.toString();
				this.accessLocation = new GeneralName({ schema: asn1.result.accessLocation });
			}
		}, {
			key: 'toSchema',
			value: function toSchema() {
				return new Sequence({
					value: [new ObjectIdentifier$1({ value: this.accessMethod }), this.accessLocation.toSchema()]
				});
			}
		}, {
			key: 'toJSON',
			value: function toJSON() {
				return {
					accessMethod: this.accessMethod,
					accessLocation: this.accessLocation.toJSON()
				};
			}
		}], [{
			key: 'defaultValues',
			value: function defaultValues(memberName) {
				switch (memberName) {
					case "accessMethod":
						return "";
					case "accessLocation":
						return new GeneralName();
					default:
						throw new Error('Invalid member name for AccessDescription class: ' + memberName);
				}
			}
		}, {
			key: 'schema',
			value: function schema() {
				var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

				var names = getParametersValue$1(parameters, "names", {});

				return new Sequence({
					name: names.blockName || "",
					value: [new ObjectIdentifier$1({ name: names.accessMethod || "" }), GeneralName.schema(names.accessLocation || {})]
				});
			}
		}]);

		return AccessDescription;
	}();

	var AltName = function () {
		function AltName() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, AltName);

			this.altNames = getParametersValue$1(parameters, "altNames", AltName.defaultValues("altNames"));

			if ("schema" in parameters) this.fromSchema(parameters.schema);
		}

		_createClass(AltName, [{
			key: 'fromSchema',
			value: function fromSchema(schema) {
				var asn1 = compareSchema(schema, schema, AltName.schema({
					names: {
						altNames: "altNames"
					}
				}));

				if (asn1.verified === false) throw new Error("Object's schema was not verified against input data for AltName");

				if ("altNames" in asn1.result) this.altNames = Array.from(asn1.result.altNames, function (element) {
					return new GeneralName({ schema: element });
				});
			}
		}, {
			key: 'toSchema',
			value: function toSchema() {
				return new Sequence({
					value: Array.from(this.altNames, function (element) {
						return element.toSchema();
					})
				});
			}
		}, {
			key: 'toJSON',
			value: function toJSON() {
				return {
					altNames: Array.from(this.altNames, function (element) {
						return element.toJSON();
					})
				};
			}
		}], [{
			key: 'defaultValues',
			value: function defaultValues(memberName) {
				switch (memberName) {
					case "altNames":
						return [];
					default:
						throw new Error('Invalid member name for AltName class: ' + memberName);
				}
			}
		}, {
			key: 'schema',
			value: function schema() {
				var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

				var names = getParametersValue$1(parameters, "names", {});

				return new Sequence({
					name: names.blockName || "",
					value: [new Repeated({
						name: names.altNames || "",
						value: GeneralName.schema()
					})]
				});
			}
		}]);

		return AltName;
	}();

	var Time = function () {
		function Time() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, Time);

			this.type = getParametersValue$1(parameters, "type", Time.defaultValues("type"));

			this.value = getParametersValue$1(parameters, "value", Time.defaultValues("value"));

			if ("schema" in parameters) this.fromSchema(parameters.schema);
		}

		_createClass(Time, [{
			key: 'fromSchema',
			value: function fromSchema(schema) {
				var asn1 = compareSchema(schema, schema, Time.schema({
					names: {
						utcTimeName: "utcTimeName",
						generalTimeName: "generalTimeName"
					}
				}));

				if (asn1.verified === false) throw new Error("Object's schema was not verified against input data for TIME");

				if ("utcTimeName" in asn1.result) {
					this.type = 0;
					this.value = asn1.result.utcTimeName.toDate();
				}
				if ("generalTimeName" in asn1.result) {
					this.type = 1;
					this.value = asn1.result.generalTimeName.toDate();
				}
			}
		}, {
			key: 'toSchema',
			value: function toSchema() {
				var result = {};

				if (this.type === 0) result = new UTCTime({ valueDate: this.value });
				if (this.type === 1) result = new GeneralizedTime({ valueDate: this.value });

				return result;
			}
		}, {
			key: 'toJSON',
			value: function toJSON() {
				return {
					type: this.type,
					value: this.value
				};
			}
		}], [{
			key: 'defaultValues',
			value: function defaultValues(memberName) {
				switch (memberName) {
					case "type":
						return 0;
					case "value":
						return new Date(0, 0, 0);
					default:
						throw new Error('Invalid member name for Time class: ' + memberName);
				}
			}
		}, {
			key: 'schema',
			value: function schema() {
				var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};
				var optional = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : false;

				var names = getParametersValue$1(parameters, "names", {});

				return new Choice({
					optional: optional,
					value: [new UTCTime({ name: names.utcTimeName || "" }), new GeneralizedTime({ name: names.generalTimeName || "" })]
				});
			}
		}]);

		return Time;
	}();

	var SubjectDirectoryAttributes = function () {
		function SubjectDirectoryAttributes() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, SubjectDirectoryAttributes);

			this.attributes = getParametersValue$1(parameters, "attributes", SubjectDirectoryAttributes.defaultValues("attributes"));

			if ("schema" in parameters) this.fromSchema(parameters.schema);
		}

		_createClass(SubjectDirectoryAttributes, [{
			key: 'fromSchema',
			value: function fromSchema(schema) {
				var asn1 = compareSchema(schema, schema, SubjectDirectoryAttributes.schema({
					names: {
						attributes: "attributes"
					}
				}));

				if (asn1.verified === false) throw new Error("Object's schema was not verified against input data for SubjectDirectoryAttributes");

				this.attributes = Array.from(asn1.result.attributes, function (element) {
					return new Attribute({ schema: element });
				});
			}
		}, {
			key: 'toSchema',
			value: function toSchema() {
				return new Sequence({
					value: Array.from(this.attributes, function (element) {
						return element.toSchema();
					})
				});
			}
		}, {
			key: 'toJSON',
			value: function toJSON() {
				return {
					attributes: Array.from(this.attributes, function (element) {
						return element.toJSON();
					})
				};
			}
		}], [{
			key: 'defaultValues',
			value: function defaultValues(memberName) {
				switch (memberName) {
					case "attributes":
						return [];
					default:
						throw new Error('Invalid member name for SubjectDirectoryAttributes class: ' + memberName);
				}
			}
		}, {
			key: 'schema',
			value: function schema() {
				var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

				var names = getParametersValue$1(parameters, "names", {});

				return new Sequence({
					name: names.blockName || "",
					value: [new Repeated({
						name: names.attributes || "",
						value: Attribute.schema()
					})]
				});
			}
		}]);

		return SubjectDirectoryAttributes;
	}();

	var PrivateKeyUsagePeriod = function () {
		function PrivateKeyUsagePeriod() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, PrivateKeyUsagePeriod);

			if ("notBefore" in parameters) this.notBefore = getParametersValue$1(parameters, "notBefore", PrivateKeyUsagePeriod.defaultValues("notBefore"));

			if ("notAfter" in parameters) this.notAfter = getParametersValue$1(parameters, "notAfter", PrivateKeyUsagePeriod.defaultValues("notAfter"));

			if ("schema" in parameters) this.fromSchema(parameters.schema);
		}

		_createClass(PrivateKeyUsagePeriod, [{
			key: 'fromSchema',
			value: function fromSchema(schema) {
				var asn1 = compareSchema(schema, schema, PrivateKeyUsagePeriod.schema({
					names: {
						notBefore: "notBefore",
						notAfter: "notAfter"
					}
				}));

				if (asn1.verified === false) throw new Error("Object's schema was not verified against input data for PrivateKeyUsagePeriod");

				if ("notBefore" in asn1.result) {
					var localNotBefore = new GeneralizedTime();
					localNotBefore.fromBuffer(asn1.result.notBefore.valueBlock.valueHex);
					this.notBefore = localNotBefore.toDate();
				}

				if ("notAfter" in asn1.result) {
					var localNotAfter = new GeneralizedTime({ valueHex: asn1.result.notAfter.valueBlock.valueHex });
					localNotAfter.fromBuffer(asn1.result.notAfter.valueBlock.valueHex);
					this.notAfter = localNotAfter.toDate();
				}
			}
		}, {
			key: 'toSchema',
			value: function toSchema() {
				var outputArray = [];

				if ("notBefore" in this) {
					outputArray.push(new Primitive({
						idBlock: {
							tagClass: 3,
							tagNumber: 0 },
						valueHex: new GeneralizedTime({ valueDate: this.notBefore }).valueBlock.valueHex
					}));
				}

				if ("notAfter" in this) {
					outputArray.push(new Primitive({
						idBlock: {
							tagClass: 3,
							tagNumber: 1 },
						valueHex: new GeneralizedTime({ valueDate: this.notAfter }).valueBlock.valueHex
					}));
				}

				return new Sequence({
					value: outputArray
				});
			}
		}, {
			key: 'toJSON',
			value: function toJSON() {
				var object = {};

				if ("notBefore" in this) object.notBefore = this.notBefore;

				if ("notAfter" in this) object.notAfter = this.notAfter;

				return object;
			}
		}], [{
			key: 'defaultValues',
			value: function defaultValues(memberName) {
				switch (memberName) {
					case "notBefore":
						return new Date();
					case "notAfter":
						return new Date();
					default:
						throw new Error('Invalid member name for PrivateKeyUsagePeriod class: ' + memberName);
				}
			}
		}, {
			key: 'schema',
			value: function schema() {
				var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

				var names = getParametersValue$1(parameters, "names", {});

				return new Sequence({
					name: names.blockName || "",
					value: [new Primitive({
						name: names.notBefore || "",
						optional: true,
						idBlock: {
							tagClass: 3,
							tagNumber: 0 }
					}), new Primitive({
						name: names.notAfter || "",
						optional: true,
						idBlock: {
							tagClass: 3,
							tagNumber: 1 }
					})]
				});
			}
		}]);

		return PrivateKeyUsagePeriod;
	}();

	var BasicConstraints = function () {
		function BasicConstraints() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, BasicConstraints);

			this.cA = getParametersValue$1(parameters, "cA", false);

			if ("pathLenConstraint" in parameters) this.pathLenConstraint = getParametersValue$1(parameters, "pathLenConstraint", 0);

			if ("schema" in parameters) this.fromSchema(parameters.schema);
		}

		_createClass(BasicConstraints, [{
			key: 'fromSchema',
			value: function fromSchema(schema) {
				var asn1 = compareSchema(schema, schema, BasicConstraints.schema({
					names: {
						cA: "cA",
						pathLenConstraint: "pathLenConstraint"
					}
				}));

				if (asn1.verified === false) throw new Error("Object's schema was not verified against input data for BasicConstraints");

				if ("cA" in asn1.result) this.cA = asn1.result.cA.valueBlock.value;

				if ("pathLenConstraint" in asn1.result) {
					if (asn1.result.pathLenConstraint.valueBlock.isHexOnly) this.pathLenConstraint = asn1.result.pathLenConstraint;else this.pathLenConstraint = asn1.result.pathLenConstraint.valueBlock.valueDec;
				}
			}
		}, {
			key: 'toSchema',
			value: function toSchema() {
				var outputArray = [];

				if (this.cA !== BasicConstraints.defaultValues("cA")) outputArray.push(new Boolean({ value: this.cA }));

				if ("pathLenConstraint" in this) {
					if (this.pathLenConstraint instanceof Integer) outputArray.push(this.pathLenConstraint);else outputArray.push(new Integer({ value: this.pathLenConstraint }));
				}

				return new Sequence({
					value: outputArray
				});
			}
		}, {
			key: 'toJSON',
			value: function toJSON() {
				var object = {};

				if (this.cA !== BasicConstraints.defaultValues("cA")) object.cA = this.cA;

				if ("pathLenConstraint" in this) {
					if (this.pathLenConstraint instanceof Integer) object.pathLenConstraint = this.pathLenConstraint.toJSON();else object.pathLenConstraint = this.pathLenConstraint;
				}

				return object;
			}
		}], [{
			key: 'defaultValues',
			value: function defaultValues(memberName) {
				switch (memberName) {
					case "cA":
						return false;
					default:
						throw new Error('Invalid member name for BasicConstraints class: ' + memberName);
				}
			}
		}, {
			key: 'schema',
			value: function schema() {
				var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

				var names = getParametersValue$1(parameters, "names", {});

				return new Sequence({
					name: names.blockName || "",
					value: [new Boolean({
						optional: true,
						name: names.cA || ""
					}), new Integer({
						optional: true,
						name: names.pathLenConstraint || ""
					})]
				});
			}
		}]);

		return BasicConstraints;
	}();

	var IssuingDistributionPoint = function () {
		function IssuingDistributionPoint() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, IssuingDistributionPoint);

			if ("distributionPoint" in parameters) this.distributionPoint = getParametersValue$1(parameters, "distributionPoint", IssuingDistributionPoint.defaultValues("distributionPoint"));

			this.onlyContainsUserCerts = getParametersValue$1(parameters, "onlyContainsUserCerts", IssuingDistributionPoint.defaultValues("onlyContainsUserCerts"));

			this.onlyContainsCACerts = getParametersValue$1(parameters, "onlyContainsCACerts", IssuingDistributionPoint.defaultValues("onlyContainsCACerts"));

			if ("onlySomeReasons" in parameters) this.onlySomeReasons = getParametersValue$1(parameters, "onlySomeReasons", IssuingDistributionPoint.defaultValues("onlySomeReasons"));

			this.indirectCRL = getParametersValue$1(parameters, "indirectCRL", IssuingDistributionPoint.defaultValues("indirectCRL"));

			this.onlyContainsAttributeCerts = getParametersValue$1(parameters, "onlyContainsAttributeCerts", IssuingDistributionPoint.defaultValues("onlyContainsAttributeCerts"));

			if ("schema" in parameters) this.fromSchema(parameters.schema);
		}

		_createClass(IssuingDistributionPoint, [{
			key: 'fromSchema',
			value: function fromSchema(schema) {
				var asn1 = compareSchema(schema, schema, IssuingDistributionPoint.schema({
					names: {
						distributionPoint: "distributionPoint",
						distributionPointNames: "distributionPointNames",
						onlyContainsUserCerts: "onlyContainsUserCerts",
						onlyContainsCACerts: "onlyContainsCACerts",
						onlySomeReasons: "onlySomeReasons",
						indirectCRL: "indirectCRL",
						onlyContainsAttributeCerts: "onlyContainsAttributeCerts"
					}
				}));

				if (asn1.verified === false) throw new Error("Object's schema was not verified against input data for IssuingDistributionPoint");

				if ("distributionPoint" in asn1.result) {
					switch (true) {
						case asn1.result.distributionPoint.idBlock.tagNumber === 0:
							this.distributionPoint = Array.from(asn1.result.distributionPointNames, function (element) {
								return new GeneralName({ schema: element });
							});
							break;
						case asn1.result.distributionPoint.idBlock.tagNumber === 1:
							{
								asn1.result.distributionPoint.idBlock.tagClass = 1;
								asn1.result.distributionPoint.idBlock.tagNumber = 16;

								this.distributionPoint = new RelativeDistinguishedNames({ schema: asn1.result.distributionPoint });
							}
							break;
						default:
							throw new Error("Unknown tagNumber for distributionPoint: {$asn1.result.distributionPoint.idBlock.tagNumber}");
					}
				}

				if ("onlyContainsUserCerts" in asn1.result) {
					var view = new Uint8Array(asn1.result.onlyContainsUserCerts.valueBlock.valueHex);
					this.onlyContainsUserCerts = view[0] !== 0x00;
				}

				if ("onlyContainsCACerts" in asn1.result) {
					var _view4 = new Uint8Array(asn1.result.onlyContainsCACerts.valueBlock.valueHex);
					this.onlyContainsCACerts = _view4[0] !== 0x00;
				}

				if ("onlySomeReasons" in asn1.result) {
					var _view5 = new Uint8Array(asn1.result.onlySomeReasons.valueBlock.valueHex);
					this.onlySomeReasons = _view5[0];
				}

				if ("indirectCRL" in asn1.result) {
					var _view6 = new Uint8Array(asn1.result.indirectCRL.valueBlock.valueHex);
					this.indirectCRL = _view6[0] !== 0x00;
				}

				if ("onlyContainsAttributeCerts" in asn1.result) {
					var _view7 = new Uint8Array(asn1.result.onlyContainsAttributeCerts.valueBlock.valueHex);
					this.onlyContainsAttributeCerts = _view7[0] !== 0x00;
				}
			}
		}, {
			key: 'toSchema',
			value: function toSchema() {
				var outputArray = [];

				if ("distributionPoint" in this) {
					var value = void 0;

					if (this.distributionPoint instanceof Array) {
						value = new Constructed({
							idBlock: {
								tagClass: 3,
								tagNumber: 0 },
							value: Array.from(this.distributionPoint, function (element) {
								return element.toSchema();
							})
						});
					} else {
						value = this.distributionPoint.toSchema();

						value.idBlock.tagClass = 3;
						value.idBlock.tagNumber = 1;
					}

					outputArray.push(value);
				}

				if (this.onlyContainsUserCerts !== IssuingDistributionPoint.defaultValues("onlyContainsUserCerts")) {
					outputArray.push(new Primitive({
						idBlock: {
							tagClass: 3,
							tagNumber: 1 },
						valueHex: new Uint8Array([0xFF]).buffer
					}));
				}

				if (this.onlyContainsCACerts !== IssuingDistributionPoint.defaultValues("onlyContainsCACerts")) {
					outputArray.push(new Primitive({
						idBlock: {
							tagClass: 3,
							tagNumber: 2 },
						valueHex: new Uint8Array([0xFF]).buffer
					}));
				}

				if ("onlySomeReasons" in this) {
					var buffer = new ArrayBuffer(1);
					var view = new Uint8Array(buffer);

					view[0] = this.onlySomeReasons;

					outputArray.push(new Primitive({
						idBlock: {
							tagClass: 3,
							tagNumber: 3 },
						valueHex: buffer
					}));
				}

				if (this.indirectCRL !== IssuingDistributionPoint.defaultValues("indirectCRL")) {
					outputArray.push(new Primitive({
						idBlock: {
							tagClass: 3,
							tagNumber: 4 },
						valueHex: new Uint8Array([0xFF]).buffer
					}));
				}

				if (this.onlyContainsAttributeCerts !== IssuingDistributionPoint.defaultValues("onlyContainsAttributeCerts")) {
					outputArray.push(new Primitive({
						idBlock: {
							tagClass: 3,
							tagNumber: 5 },
						valueHex: new Uint8Array([0xFF]).buffer
					}));
				}

				return new Sequence({
					value: outputArray
				});
			}
		}, {
			key: 'toJSON',
			value: function toJSON() {
				var object = {};

				if ("distributionPoint" in this) {
					if (this.distributionPoint instanceof Array) object.distributionPoint = Array.from(this.distributionPoint, function (element) {
						return element.toJSON();
					});else object.distributionPoint = this.distributionPoint.toJSON();
				}

				if (this.onlyContainsUserCerts !== IssuingDistributionPoint.defaultValues("onlyContainsUserCerts")) object.onlyContainsUserCerts = this.onlyContainsUserCerts;

				if (this.onlyContainsCACerts !== IssuingDistributionPoint.defaultValues("onlyContainsCACerts")) object.onlyContainsCACerts = this.onlyContainsCACerts;

				if ("onlySomeReasons" in this) object.onlySomeReasons = this.onlySomeReasons;

				if (this.indirectCRL !== IssuingDistributionPoint.defaultValues("indirectCRL")) object.indirectCRL = this.indirectCRL;

				if (this.onlyContainsAttributeCerts !== IssuingDistributionPoint.defaultValues("onlyContainsAttributeCerts")) object.onlyContainsAttributeCerts = this.onlyContainsAttributeCerts;

				return object;
			}
		}], [{
			key: 'defaultValues',
			value: function defaultValues(memberName) {
				switch (memberName) {
					case "distributionPoint":
						return [];
					case "onlyContainsUserCerts":
						return false;
					case "onlyContainsCACerts":
						return false;
					case "onlySomeReasons":
						return 0;
					case "indirectCRL":
						return false;
					case "onlyContainsAttributeCerts":
						return false;
					default:
						throw new Error('Invalid member name for IssuingDistributionPoint class: ' + memberName);
				}
			}
		}, {
			key: 'schema',
			value: function schema() {
				var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

				var names = getParametersValue$1(parameters, "names", {});

				return new Sequence({
					name: names.blockName || "",
					value: [new Constructed({
						optional: true,
						idBlock: {
							tagClass: 3,
							tagNumber: 0 },
						value: [new Choice({
							value: [new Constructed({
								name: names.distributionPoint || "",
								idBlock: {
									tagClass: 3,
									tagNumber: 0 },
								value: [new Repeated({
									name: names.distributionPointNames || "",
									value: GeneralName.schema()
								})]
							}), new Constructed({
								name: names.distributionPoint || "",
								idBlock: {
									tagClass: 3,
									tagNumber: 1 },
								value: RelativeDistinguishedNames.schema().valueBlock.value
							})]
						})]
					}), new Primitive({
						name: names.onlyContainsUserCerts || "",
						optional: true,
						idBlock: {
							tagClass: 3,
							tagNumber: 1 }
					}), new Primitive({
						name: names.onlyContainsCACerts || "",
						optional: true,
						idBlock: {
							tagClass: 3,
							tagNumber: 2 }
					}), new Primitive({
						name: names.onlySomeReasons || "",
						optional: true,
						idBlock: {
							tagClass: 3,
							tagNumber: 3 }
					}), new Primitive({
						name: names.indirectCRL || "",
						optional: true,
						idBlock: {
							tagClass: 3,
							tagNumber: 4 }
					}), new Primitive({
						name: names.onlyContainsAttributeCerts || "",
						optional: true,
						idBlock: {
							tagClass: 3,
							tagNumber: 5 }
					})]
				});
			}
		}]);

		return IssuingDistributionPoint;
	}();

	var GeneralNames = function () {
		function GeneralNames() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, GeneralNames);

			this.names = getParametersValue$1(parameters, "names", GeneralNames.defaultValues("names"));

			if ("schema" in parameters) this.fromSchema(parameters.schema);
		}

		_createClass(GeneralNames, [{
			key: 'fromSchema',
			value: function fromSchema(schema) {
				var asn1 = compareSchema(schema, schema, GeneralNames.schema({
					names: {
						blockName: "names",
						generalNames: "generalNames"
					}
				}));

				if (asn1.verified === false) throw new Error("Object's schema was not verified against input data for GeneralNames");

				this.names = Array.from(asn1.result.generalNames, function (element) {
					return new GeneralName({ schema: element });
				});
			}
		}, {
			key: 'toSchema',
			value: function toSchema() {
				return new Sequence({
					value: Array.from(this.names, function (element) {
						return element.toSchema();
					})
				});
			}
		}, {
			key: 'toJSON',
			value: function toJSON() {
				return {
					names: Array.from(this.names, function (element) {
						return element.toJSON();
					})
				};
			}
		}], [{
			key: 'defaultValues',
			value: function defaultValues(memberName) {
				switch (memberName) {
					case "names":
						return [];
					default:
						throw new Error('Invalid member name for GeneralNames class: ' + memberName);
				}
			}
		}, {
			key: 'schema',
			value: function schema() {
				var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};
				var optional = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : false;

				var names = getParametersValue$1(parameters, "names", {});

				return new Sequence({
					optional: optional,
					name: names.blockName || "",
					value: [new Repeated({
						name: names.generalNames || "",
						value: GeneralName.schema()
					})]
				});
			}
		}]);

		return GeneralNames;
	}();

	var GeneralSubtree = function () {
		function GeneralSubtree() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, GeneralSubtree);

			this.base = getParametersValue$1(parameters, "base", GeneralSubtree.defaultValues("base"));

			this.minimum = getParametersValue$1(parameters, "minimum", GeneralSubtree.defaultValues("minimum"));

			if ("maximum" in parameters) this.maximum = getParametersValue$1(parameters, "maximum", GeneralSubtree.defaultValues("maximum"));

			if ("schema" in parameters) this.fromSchema(parameters.schema);
		}

		_createClass(GeneralSubtree, [{
			key: 'fromSchema',
			value: function fromSchema(schema) {
				var asn1 = compareSchema(schema, schema, GeneralSubtree.schema({
					names: {
						base: {
							names: {
								blockName: "base"
							}
						},
						minimum: "minimum",
						maximum: "maximum"
					}
				}));

				if (asn1.verified === false) throw new Error("Object's schema was not verified against input data for ");

				this.base = new GeneralName({ schema: asn1.result.base });

				if ("minimum" in asn1.result) {
					if (asn1.result.minimum.valueBlock.isHexOnly) this.minimum = asn1.result.minimum;else this.minimum = asn1.result.minimum.valueBlock.valueDec;
				}

				if ("maximum" in asn1.result) {
					if (asn1.result.maximum.valueBlock.isHexOnly) this.maximum = asn1.result.maximum;else this.maximum = asn1.result.maximum.valueBlock.valueDec;
				}
			}
		}, {
			key: 'toSchema',
			value: function toSchema() {
				var outputArray = [];

				outputArray.push(this.base.toSchema());

				if (this.minimum !== 0) {
					var valueMinimum = 0;

					if (this.minimum instanceof Integer) valueMinimum = this.minimum;else valueMinimum = new Integer({ value: this.minimum });

					outputArray.push(new Constructed({
						optional: true,
						idBlock: {
							tagClass: 3,
							tagNumber: 0 },
						value: [valueMinimum]
					}));
				}

				if ("maximum" in this) {
					var valueMaximum = 0;

					if (this.maximum instanceof Integer) valueMaximum = this.maximum;else valueMaximum = new Integer({ value: this.maximum });

					outputArray.push(new Constructed({
						optional: true,
						idBlock: {
							tagClass: 3,
							tagNumber: 1 },
						value: [valueMaximum]
					}));
				}

				return new Sequence({
					value: outputArray
				});
			}
		}, {
			key: 'toJSON',
			value: function toJSON() {
				var object = {
					base: this.base.toJSON()
				};

				if (this.minimum !== 0) {
					if (typeof this.minimum === "number") object.minimum = this.minimum;else object.minimum = this.minimum.toJSON();
				}

				if ("maximum" in this) {
					if (typeof this.maximum === "number") object.maximum = this.maximum;else object.maximum = this.maximum.toJSON();
				}

				return object;
			}
		}], [{
			key: 'defaultValues',
			value: function defaultValues(memberName) {
				switch (memberName) {
					case "base":
						return new GeneralName();
					case "minimum":
						return 0;
					case "maximum":
						return 0;
					default:
						throw new Error('Invalid member name for GeneralSubtree class: ' + memberName);
				}
			}
		}, {
			key: 'schema',
			value: function schema() {
				var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

				var names = getParametersValue$1(parameters, "names", {});

				return new Sequence({
					name: names.blockName || "",
					value: [GeneralName.schema(names.base || {}), new Constructed({
						optional: true,
						idBlock: {
							tagClass: 3,
							tagNumber: 0 },
						value: [new Integer({ name: names.minimum || "" })]
					}), new Constructed({
						optional: true,
						idBlock: {
							tagClass: 3,
							tagNumber: 1 },
						value: [new Integer({ name: names.maximum || "" })]
					})]
				});
			}
		}]);

		return GeneralSubtree;
	}();

	var NameConstraints = function () {
		function NameConstraints() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, NameConstraints);

			if ("permittedSubtrees" in parameters) this.permittedSubtrees = getParametersValue$1(parameters, "permittedSubtrees", NameConstraints.defaultValues("permittedSubtrees"));

			if ("excludedSubtrees" in parameters) this.excludedSubtrees = getParametersValue$1(parameters, "excludedSubtrees", NameConstraints.defaultValues("excludedSubtrees"));

			if ("schema" in parameters) this.fromSchema(parameters.schema);
		}

		_createClass(NameConstraints, [{
			key: 'fromSchema',
			value: function fromSchema(schema) {
				var asn1 = compareSchema(schema, schema, NameConstraints.schema({
					names: {
						permittedSubtrees: "permittedSubtrees",
						excludedSubtrees: "excludedSubtrees"
					}
				}));

				if (asn1.verified === false) throw new Error("Object's schema was not verified against input data for NameConstraints");

				if ("permittedSubtrees" in asn1.result) this.permittedSubtrees = Array.from(asn1.result.permittedSubtrees, function (element) {
					return new GeneralSubtree({ schema: element });
				});

				if ("excludedSubtrees" in asn1.result) this.excludedSubtrees = Array.from(asn1.result.excludedSubtrees, function (element) {
					return new GeneralSubtree({ schema: element });
				});
			}
		}, {
			key: 'toSchema',
			value: function toSchema() {
				var outputArray = [];

				if ("permittedSubtrees" in this) {
					outputArray.push(new Constructed({
						optional: true,
						idBlock: {
							tagClass: 3,
							tagNumber: 0 },
						value: [new Sequence({
							value: Array.from(this.permittedSubtrees, function (element) {
								return element.toSchema();
							})
						})]
					}));
				}

				if ("excludedSubtrees" in this) {
					outputArray.push(new Constructed({
						optional: true,
						idBlock: {
							tagClass: 3,
							tagNumber: 1 },
						value: [new Sequence({
							value: Array.from(this.excludedSubtrees, function (element) {
								return element.toSchema();
							})
						})]
					}));
				}

				return new Sequence({
					value: outputArray
				});
			}
		}, {
			key: 'toJSON',
			value: function toJSON() {
				var object = {};

				if ("permittedSubtrees" in this) object.permittedSubtrees = Array.from(this.permittedSubtrees, function (element) {
					return element.toJSON();
				});

				if ("excludedSubtrees" in this) object.excludedSubtrees = Array.from(this.excludedSubtrees, function (element) {
					return element.toJSON();
				});

				return object;
			}
		}], [{
			key: 'defaultValues',
			value: function defaultValues(memberName) {
				switch (memberName) {
					case "permittedSubtrees":
						return [];
					case "excludedSubtrees":
						return [];
					default:
						throw new Error('Invalid member name for NameConstraints class: ' + memberName);
				}
			}
		}, {
			key: 'schema',
			value: function schema() {
				var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

				var names = getParametersValue$1(parameters, "names", {});

				return new Sequence({
					name: names.blockName || "",
					value: [new Constructed({
						optional: true,
						idBlock: {
							tagClass: 3,
							tagNumber: 0 },
						value: [new Repeated({
							name: names.permittedSubtrees || "",
							value: GeneralSubtree.schema()
						})]
					}), new Constructed({
						optional: true,
						idBlock: {
							tagClass: 3,
							tagNumber: 1 },
						value: [new Repeated({
							name: names.excludedSubtrees || "",
							value: GeneralSubtree.schema()
						})]
					})]
				});
			}
		}]);

		return NameConstraints;
	}();

	var DistributionPoint = function () {
		function DistributionPoint() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, DistributionPoint);

			if ("distributionPoint" in parameters) this.distributionPoint = getParametersValue$1(parameters, "distributionPoint", DistributionPoint.defaultValues("distributionPoint"));

			if ("reasons" in parameters) this.reasons = getParametersValue$1(parameters, "reasons", DistributionPoint.defaultValues("reasons"));

			if ("cRLIssuer" in parameters) this.cRLIssuer = getParametersValue$1(parameters, "cRLIssuer", DistributionPoint.defaultValues("cRLIssuer"));

			if ("schema" in parameters) this.fromSchema(parameters.schema);
		}

		_createClass(DistributionPoint, [{
			key: 'fromSchema',
			value: function fromSchema(schema) {
				var asn1 = compareSchema(schema, schema, DistributionPoint.schema({
					names: {
						distributionPoint: "distributionPoint",
						distributionPointNames: "distributionPointNames",
						reasons: "reasons",
						cRLIssuer: "cRLIssuer",
						cRLIssuerNames: "cRLIssuerNames"
					}
				}));

				if (asn1.verified === false) throw new Error("Object's schema was not verified against input data for DistributionPoint");

				if ("distributionPoint" in asn1.result) {
					if (asn1.result.distributionPoint.idBlock.tagNumber === 0) this.distributionPoint = Array.from(asn1.result.distributionPointNames, function (element) {
							return new GeneralName({ schema: element });
						});

					if (asn1.result.distributionPoint.idBlock.tagNumber === 1) {
							asn1.result.distributionPoint.idBlock.tagClass = 1;
							asn1.result.distributionPoint.idBlock.tagNumber = 16;

							this.distributionPoint = new RelativeDistinguishedNames({ schema: asn1.result.distributionPoint });
						}
				}

				if ("reasons" in asn1.result) this.reasons = new BitString({ valueHex: asn1.result.reasons.valueBlock.valueHex });

				if ("cRLIssuer" in asn1.result) this.cRLIssuer = Array.from(asn1.result.cRLIssuerNames, function (element) {
					return new GeneralName({ schema: element });
				});
			}
		}, {
			key: 'toSchema',
			value: function toSchema() {
				var outputArray = [];

				if ("distributionPoint" in this) {
					var internalValue = void 0;

					if (this.distributionPoint instanceof Array) {
						internalValue = new Constructed({
							idBlock: {
								tagClass: 3,
								tagNumber: 0 },
							value: Array.from(this.distributionPoint, function (element) {
								return element.toSchema();
							})
						});
					} else {
						internalValue = new Constructed({
							idBlock: {
								tagClass: 3,
								tagNumber: 1 },
							value: [this.distributionPoint.toSchema()]
						});
					}

					outputArray.push(new Constructed({
						idBlock: {
							tagClass: 3,
							tagNumber: 0 },
						value: [internalValue]
					}));
				}

				if ("reasons" in this) {
					outputArray.push(new Primitive({
						idBlock: {
							tagClass: 3,
							tagNumber: 1 },
						valueHex: this.reasons.valueBlock.valueHex
					}));
				}

				if ("cRLIssuer" in this) {
					outputArray.push(new Constructed({
						idBlock: {
							tagClass: 3,
							tagNumber: 2 },
						value: Array.from(this.cRLIssuer, function (element) {
							return element.toSchema();
						})
					}));
				}

				return new Sequence({
					value: outputArray
				});
			}
		}, {
			key: 'toJSON',
			value: function toJSON() {
				var object = {};

				if ("distributionPoint" in this) {
					if (this.distributionPoint instanceof Array) object.distributionPoint = Array.from(this.distributionPoint, function (element) {
						return element.toJSON();
					});else object.distributionPoint = this.distributionPoint.toJSON();
				}

				if ("reasons" in this) object.reasons = this.reasons.toJSON();

				if ("cRLIssuer" in this) object.cRLIssuer = Array.from(this.cRLIssuer, function (element) {
					return element.toJSON();
				});

				return object;
			}
		}], [{
			key: 'defaultValues',
			value: function defaultValues(memberName) {
				switch (memberName) {
					case "distributionPoint":
						return [];
					case "reasons":
						return new BitString();
					case "cRLIssuer":
						return [];
					default:
						throw new Error('Invalid member name for DistributionPoint class: ' + memberName);
				}
			}
		}, {
			key: 'schema',
			value: function schema() {
				var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

				var names = getParametersValue$1(parameters, "names", {});

				return new Sequence({
					name: names.blockName || "",
					value: [new Constructed({
						optional: true,
						idBlock: {
							tagClass: 3,
							tagNumber: 0 },
						value: [new Choice({
							value: [new Constructed({
								name: names.distributionPoint || "",
								optional: true,
								idBlock: {
									tagClass: 3,
									tagNumber: 0 },
								value: [new Repeated({
									name: names.distributionPointNames || "",
									value: GeneralName.schema()
								})]
							}), new Constructed({
								name: names.distributionPoint || "",
								optional: true,
								idBlock: {
									tagClass: 3,
									tagNumber: 1 },
								value: RelativeDistinguishedNames.schema().valueBlock.value
							})]
						})]
					}), new Primitive({
						name: names.reasons || "",
						optional: true,
						idBlock: {
							tagClass: 3,
							tagNumber: 1 }
					}), new Constructed({
						name: names.cRLIssuer || "",
						optional: true,
						idBlock: {
							tagClass: 3,
							tagNumber: 2 },
						value: [new Repeated({
							name: names.cRLIssuerNames || "",
							value: GeneralName.schema()
						})]
					})]
				});
			}
		}]);

		return DistributionPoint;
	}();

	var CRLDistributionPoints = function () {
		function CRLDistributionPoints() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, CRLDistributionPoints);

			this.distributionPoints = getParametersValue$1(parameters, "distributionPoints", CRLDistributionPoints.defaultValues("distributionPoints"));

			if ("schema" in parameters) this.fromSchema(parameters.schema);
		}

		_createClass(CRLDistributionPoints, [{
			key: 'fromSchema',
			value: function fromSchema(schema) {
				var asn1 = compareSchema(schema, schema, CRLDistributionPoints.schema({
					names: {
						distributionPoints: "distributionPoints"
					}
				}));

				if (asn1.verified === false) throw new Error("Object's schema was not verified against input data for CRLDistributionPoints");

				this.distributionPoints = Array.from(asn1.result.distributionPoints, function (element) {
					return new DistributionPoint({ schema: element });
				});
			}
		}, {
			key: 'toSchema',
			value: function toSchema() {
				return new Sequence({
					value: Array.from(this.distributionPoints, function (element) {
						return element.toSchema();
					})
				});
			}
		}, {
			key: 'toJSON',
			value: function toJSON() {
				return {
					distributionPoints: Array.from(this.distributionPoints, function (element) {
						return element.toJSON();
					})
				};
			}
		}], [{
			key: 'defaultValues',
			value: function defaultValues(memberName) {
				switch (memberName) {
					case "distributionPoints":
						return [];
					default:
						throw new Error('Invalid member name for CRLDistributionPoints class: ' + memberName);
				}
			}
		}, {
			key: 'schema',
			value: function schema() {
				var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

				var names = getParametersValue$1(parameters, "names", {});

				return new Sequence({
					name: names.blockName || "",
					value: [new Repeated({
						name: names.distributionPoints || "",
						value: DistributionPoint.schema()
					})]
				});
			}
		}]);

		return CRLDistributionPoints;
	}();

	var PolicyQualifierInfo = function () {
		function PolicyQualifierInfo() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, PolicyQualifierInfo);

			this.policyQualifierId = getParametersValue$1(parameters, "policyQualifierId", PolicyQualifierInfo.defaultValues("policyQualifierId"));

			this.qualifier = getParametersValue$1(parameters, "qualifier", PolicyQualifierInfo.defaultValues("qualifier"));

			if ("schema" in parameters) this.fromSchema(parameters.schema);
		}

		_createClass(PolicyQualifierInfo, [{
			key: 'fromSchema',
			value: function fromSchema(schema) {
				var asn1 = compareSchema(schema, schema, PolicyQualifierInfo.schema({
					names: {
						policyQualifierId: "policyQualifierId",
						qualifier: "qualifier"
					}
				}));

				if (asn1.verified === false) throw new Error("Object's schema was not verified against input data for PolicyQualifierInfo");

				this.policyQualifierId = asn1.result.policyQualifierId.valueBlock.toString();
				this.qualifier = asn1.result.qualifier;
			}
		}, {
			key: 'toSchema',
			value: function toSchema() {
				return new Sequence({
					value: [new ObjectIdentifier$1({ value: this.policyQualifierId }), this.qualifier]
				});
			}
		}, {
			key: 'toJSON',
			value: function toJSON() {
				return {
					policyQualifierId: this.policyQualifierId,
					qualifier: this.qualifier.toJSON()
				};
			}
		}], [{
			key: 'defaultValues',
			value: function defaultValues(memberName) {
				switch (memberName) {
					case "policyQualifierId":
						return "";
					case "qualifier":
						return new Any$1();
					default:
						throw new Error('Invalid member name for PolicyQualifierInfo class: ' + memberName);
				}
			}
		}, {
			key: 'schema',
			value: function schema() {
				var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

				var names = getParametersValue$1(parameters, "names", {});

				return new Sequence({
					name: names.blockName || "",
					value: [new ObjectIdentifier$1({ name: names.policyQualifierId || "" }), new Any$1({ name: names.qualifier || "" })]
				});
			}
		}]);

		return PolicyQualifierInfo;
	}();

	var PolicyInformation = function () {
		function PolicyInformation() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, PolicyInformation);

			this.policyIdentifier = getParametersValue$1(parameters, "policyIdentifier", PolicyInformation.defaultValues("policyIdentifier"));

			if ("policyQualifiers" in parameters) this.policyQualifiers = getParametersValue$1(parameters, "policyQualifiers", PolicyInformation.defaultValues("policyQualifiers"));

			if ("schema" in parameters) this.fromSchema(parameters.schema);
		}

		_createClass(PolicyInformation, [{
			key: 'fromSchema',
			value: function fromSchema(schema) {
				var asn1 = compareSchema(schema, schema, PolicyInformation.schema({
					names: {
						policyIdentifier: "policyIdentifier",
						policyQualifiers: "policyQualifiers"
					}
				}));

				if (asn1.verified === false) throw new Error("Object's schema was not verified against input data for PolicyInformation");

				this.policyIdentifier = asn1.result.policyIdentifier.valueBlock.toString();

				if ("policyQualifiers" in asn1.result) this.policyQualifiers = Array.from(asn1.result.policyQualifiers, function (element) {
					return new PolicyQualifierInfo({ schema: element });
				});
			}
		}, {
			key: 'toSchema',
			value: function toSchema() {
				var outputArray = [];

				outputArray.push(new ObjectIdentifier$1({ value: this.policyIdentifier }));

				if ("policyQualifiers" in this) {
					outputArray.push(new Sequence({
						value: Array.from(this.policyQualifiers, function (element) {
							return element.toSchema();
						})
					}));
				}

				return new Sequence({
					value: outputArray
				});
			}
		}, {
			key: 'toJSON',
			value: function toJSON() {
				var object = {
					policyIdentifier: this.policyIdentifier
				};

				if ("policyQualifiers" in this) object.policyQualifiers = Array.from(this.policyQualifiers, function (element) {
					return element.toJSON();
				});

				return object;
			}
		}], [{
			key: 'defaultValues',
			value: function defaultValues(memberName) {
				switch (memberName) {
					case "policyIdentifier":
						return "";
					case "policyQualifiers":
						return [];
					default:
						throw new Error('Invalid member name for PolicyInformation class: ' + memberName);
				}
			}
		}, {
			key: 'schema',
			value: function schema() {
				var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

				var names = getParametersValue$1(parameters, "names", {});

				return new Sequence({
					name: names.blockName || "",
					value: [new ObjectIdentifier$1({ name: names.policyIdentifier || "" }), new Sequence({
						optional: true,
						value: [new Repeated({
							name: names.policyQualifiers || "",
							value: PolicyQualifierInfo.schema()
						})]
					})]
				});
			}
		}]);

		return PolicyInformation;
	}();

	var CertificatePolicies = function () {
		function CertificatePolicies() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, CertificatePolicies);

			this.certificatePolicies = getParametersValue$1(parameters, "certificatePolicies", CertificatePolicies.defaultValues("certificatePolicies"));

			if ("schema" in parameters) this.fromSchema(parameters.schema);
		}

		_createClass(CertificatePolicies, [{
			key: 'fromSchema',
			value: function fromSchema(schema) {
				var asn1 = compareSchema(schema, schema, CertificatePolicies.schema({
					names: {
						certificatePolicies: "certificatePolicies"
					}
				}));

				if (asn1.verified === false) throw new Error("Object's schema was not verified against input data for CertificatePolicies");

				this.certificatePolicies = Array.from(asn1.result.certificatePolicies, function (element) {
					return new PolicyInformation({ schema: element });
				});
			}
		}, {
			key: 'toSchema',
			value: function toSchema() {
				return new Sequence({
					value: Array.from(this.certificatePolicies, function (element) {
						return element.toSchema();
					})
				});
			}
		}, {
			key: 'toJSON',
			value: function toJSON() {
				return {
					certificatePolicies: Array.from(this.certificatePolicies, function (element) {
						return element.toJSON();
					})
				};
			}
		}], [{
			key: 'defaultValues',
			value: function defaultValues(memberName) {
				switch (memberName) {
					case "certificatePolicies":
						return [];
					default:
						throw new Error('Invalid member name for CertificatePolicies class: ' + memberName);
				}
			}
		}, {
			key: 'schema',
			value: function schema() {
				var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

				var names = getParametersValue$1(parameters, "names", {});

				return new Sequence({
					name: names.blockName || "",
					value: [new Repeated({
						name: names.certificatePolicies || "",
						value: PolicyInformation.schema()
					})]
				});
			}
		}]);

		return CertificatePolicies;
	}();

	var PolicyMapping = function () {
		function PolicyMapping() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, PolicyMapping);

			this.issuerDomainPolicy = getParametersValue$1(parameters, "issuerDomainPolicy", PolicyMapping.defaultValues("issuerDomainPolicy"));

			this.subjectDomainPolicy = getParametersValue$1(parameters, "subjectDomainPolicy", PolicyMapping.defaultValues("subjectDomainPolicy"));

			if ("schema" in parameters) this.fromSchema(parameters.schema);
		}

		_createClass(PolicyMapping, [{
			key: 'fromSchema',
			value: function fromSchema(schema) {
				var asn1 = compareSchema(schema, schema, PolicyMapping.schema({
					names: {
						issuerDomainPolicy: "issuerDomainPolicy",
						subjectDomainPolicy: "subjectDomainPolicy"
					}
				}));

				if (asn1.verified === false) throw new Error("Object's schema was not verified against input data for PolicyMapping");

				this.issuerDomainPolicy = asn1.result.issuerDomainPolicy.valueBlock.toString();
				this.subjectDomainPolicy = asn1.result.subjectDomainPolicy.valueBlock.toString();
			}
		}, {
			key: 'toSchema',
			value: function toSchema() {
				return new Sequence({
					value: [new ObjectIdentifier$1({ value: this.issuerDomainPolicy }), new ObjectIdentifier$1({ value: this.subjectDomainPolicy })]
				});
			}
		}, {
			key: 'toJSON',
			value: function toJSON() {
				return {
					issuerDomainPolicy: this.issuerDomainPolicy,
					subjectDomainPolicy: this.subjectDomainPolicy
				};
			}
		}], [{
			key: 'defaultValues',
			value: function defaultValues(memberName) {
				switch (memberName) {
					case "issuerDomainPolicy":
						return "";
					case "subjectDomainPolicy":
						return "";
					default:
						throw new Error('Invalid member name for PolicyMapping class: ' + memberName);
				}
			}
		}, {
			key: 'schema',
			value: function schema() {
				var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

				var names = getParametersValue$1(parameters, "names", {});

				return new Sequence({
					name: names.blockName || "",
					value: [new ObjectIdentifier$1({ name: names.issuerDomainPolicy || "" }), new ObjectIdentifier$1({ name: names.subjectDomainPolicy || "" })]
				});
			}
		}]);

		return PolicyMapping;
	}();

	var PolicyMappings = function () {
		function PolicyMappings() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, PolicyMappings);

			this.mappings = getParametersValue$1(parameters, "mappings", PolicyMappings.defaultValues("mappings"));

			if ("schema" in parameters) this.fromSchema(parameters.schema);
		}

		_createClass(PolicyMappings, [{
			key: 'fromSchema',
			value: function fromSchema(schema) {
				var asn1 = compareSchema(schema, schema, PolicyMappings.schema({
					names: {
						mappings: "mappings"
					}
				}));

				if (asn1.verified === false) throw new Error("Object's schema was not verified against input data for PolicyMappings");

				this.mappings = Array.from(asn1.result.mappings, function (element) {
					return new PolicyMapping({ schema: element });
				});
			}
		}, {
			key: 'toSchema',
			value: function toSchema() {
				return new Sequence({
					value: Array.from(this.mappings, function (element) {
						return element.toSchema();
					})
				});
			}
		}, {
			key: 'toJSON',
			value: function toJSON() {
				return {
					mappings: Array.from(this.mappings, function (element) {
						return element.toJSON();
					})
				};
			}
		}], [{
			key: 'defaultValues',
			value: function defaultValues(memberName) {
				switch (memberName) {
					case "mappings":
						return [];
					default:
						throw new Error('Invalid member name for PolicyMappings class: ' + memberName);
				}
			}
		}, {
			key: 'schema',
			value: function schema() {
				var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

				var names = getParametersValue$1(parameters, "names", {});

				return new Sequence({
					name: names.blockName || "",
					value: [new Repeated({
						name: names.mappings || "",
						value: PolicyMapping.schema()
					})]
				});
			}
		}]);

		return PolicyMappings;
	}();

	var AuthorityKeyIdentifier = function () {
		function AuthorityKeyIdentifier() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, AuthorityKeyIdentifier);

			if ("keyIdentifier" in parameters) this.keyIdentifier = getParametersValue$1(parameters, "keyIdentifier", AuthorityKeyIdentifier.defaultValues("keyIdentifier"));

			if ("authorityCertIssuer" in parameters) this.authorityCertIssuer = getParametersValue$1(parameters, "authorityCertIssuer", AuthorityKeyIdentifier.defaultValues("authorityCertIssuer"));

			if ("authorityCertSerialNumber" in parameters) this.authorityCertSerialNumber = getParametersValue$1(parameters, "authorityCertSerialNumber", AuthorityKeyIdentifier.defaultValues("authorityCertSerialNumber"));

			if ("schema" in parameters) this.fromSchema(parameters.schema);
		}

		_createClass(AuthorityKeyIdentifier, [{
			key: 'fromSchema',
			value: function fromSchema(schema) {
				var asn1 = compareSchema(schema, schema, AuthorityKeyIdentifier.schema({
					names: {
						keyIdentifier: "keyIdentifier",
						authorityCertIssuer: "authorityCertIssuer",
						authorityCertSerialNumber: "authorityCertSerialNumber"
					}
				}));

				if (asn1.verified === false) throw new Error("Object's schema was not verified against input data for AuthorityKeyIdentifier");

				if ("keyIdentifier" in asn1.result) {
					asn1.result.keyIdentifier.idBlock.tagClass = 1;
					asn1.result.keyIdentifier.idBlock.tagNumber = 4;

					this.keyIdentifier = asn1.result.keyIdentifier;
				}

				if ("authorityCertIssuer" in asn1.result) this.authorityCertIssuer = Array.from(asn1.result.authorityCertIssuer, function (element) {
					return new GeneralName({ schema: element });
				});

				if ("authorityCertSerialNumber" in asn1.result) {
					asn1.result.authorityCertSerialNumber.idBlock.tagClass = 1;
					asn1.result.authorityCertSerialNumber.idBlock.tagNumber = 2;

					this.authorityCertSerialNumber = asn1.result.authorityCertSerialNumber;
				}
			}
		}, {
			key: 'toSchema',
			value: function toSchema() {
				var outputArray = [];

				if ("keyIdentifier" in this) {
					var value = this.keyIdentifier;

					value.idBlock.tagClass = 3;
					value.idBlock.tagNumber = 0;

					outputArray.push(value);
				}

				if ("authorityCertIssuer" in this) {
					outputArray.push(new Constructed({
						optional: true,
						idBlock: {
							tagClass: 3,
							tagNumber: 1 },
						value: Array.from(this.authorityCertIssuer, function (element) {
							return element.toSchema();
						})
					}));
				}

				if ("authorityCertSerialNumber" in this) {
					var _value10 = this.authorityCertSerialNumber;

					_value10.idBlock.tagClass = 3;
					_value10.idBlock.tagNumber = 2;

					outputArray.push(_value10);
				}

				return new Sequence({
					value: outputArray
				});
			}
		}, {
			key: 'toJSON',
			value: function toJSON() {
				var object = {};

				if ("keyIdentifier" in this) object.keyIdentifier = this.keyIdentifier.toJSON();

				if ("authorityCertIssuer" in this) object.authorityCertIssuer = Array.from(this.authorityCertIssuer, function (element) {
					return element.toJSON();
				});

				if ("authorityCertSerialNumber" in this) object.authorityCertSerialNumber = this.authorityCertSerialNumber.toJSON();

				return object;
			}
		}], [{
			key: 'defaultValues',
			value: function defaultValues(memberName) {
				switch (memberName) {
					case "keyIdentifier":
						return new OctetString();
					case "authorityCertIssuer":
						return [];
					case "authorityCertSerialNumber":
						return new Integer();
					default:
						throw new Error('Invalid member name for AuthorityKeyIdentifier class: ' + memberName);
				}
			}
		}, {
			key: 'schema',
			value: function schema() {
				var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

				var names = getParametersValue$1(parameters, "names", {});

				return new Sequence({
					name: names.blockName || "",
					value: [new Primitive({
						name: names.keyIdentifier || "",
						optional: true,
						idBlock: {
							tagClass: 3,
							tagNumber: 0 }
					}), new Constructed({
						optional: true,
						idBlock: {
							tagClass: 3,
							tagNumber: 1 },
						value: [new Repeated({
							name: names.authorityCertIssuer || "",
							value: GeneralName.schema()
						})]
					}), new Primitive({
						name: names.authorityCertSerialNumber || "",
						optional: true,
						idBlock: {
							tagClass: 3,
							tagNumber: 2 }
					})]
				});
			}
		}]);

		return AuthorityKeyIdentifier;
	}();

	var PolicyConstraints = function () {
		function PolicyConstraints() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, PolicyConstraints);

			if ("requireExplicitPolicy" in parameters) this.requireExplicitPolicy = getParametersValue$1(parameters, "requireExplicitPolicy", PolicyConstraints.defaultValues("requireExplicitPolicy"));

			if ("inhibitPolicyMapping" in parameters) this.inhibitPolicyMapping = getParametersValue$1(parameters, "inhibitPolicyMapping", PolicyConstraints.defaultValues("inhibitPolicyMapping"));

			if ("schema" in parameters) this.fromSchema(parameters.schema);
		}

		_createClass(PolicyConstraints, [{
			key: 'fromSchema',
			value: function fromSchema(schema) {
				var asn1 = compareSchema(schema, schema, PolicyConstraints.schema({
					names: {
						requireExplicitPolicy: "requireExplicitPolicy",
						inhibitPolicyMapping: "inhibitPolicyMapping"
					}
				}));

				if (asn1.verified === false) throw new Error("Object's schema was not verified against input data for PolicyConstraints");

				if ("requireExplicitPolicy" in asn1.result) {
					var field1 = asn1.result.requireExplicitPolicy;

					field1.idBlock.tagClass = 1;
					field1.idBlock.tagNumber = 2;

					var ber1 = field1.toBER(false);
					var int1 = fromBER(ber1);

					this.requireExplicitPolicy = int1.result.valueBlock.valueDec;
				}

				if ("inhibitPolicyMapping" in asn1.result) {
					var field2 = asn1.result.inhibitPolicyMapping;

					field2.idBlock.tagClass = 1;
					field2.idBlock.tagNumber = 2;

					var ber2 = field2.toBER(false);
					var int2 = fromBER(ber2);

					this.inhibitPolicyMapping = int2.result.valueBlock.valueDec;
				}
			}
		}, {
			key: 'toSchema',
			value: function toSchema() {
				var outputArray = [];

				if ("requireExplicitPolicy" in this) {
					var int1 = new Integer({ value: this.requireExplicitPolicy });

					int1.idBlock.tagClass = 3;
					int1.idBlock.tagNumber = 0;

					outputArray.push(int1);
				}

				if ("inhibitPolicyMapping" in this) {
					var int2 = new Integer({ value: this.inhibitPolicyMapping });

					int2.idBlock.tagClass = 3;
					int2.idBlock.tagNumber = 1;

					outputArray.push(int2);
				}

				return new Sequence({
					value: outputArray
				});
			}
		}, {
			key: 'toJSON',
			value: function toJSON() {
				var object = {};

				if ("requireExplicitPolicy" in this) object.requireExplicitPolicy = this.requireExplicitPolicy;

				if ("inhibitPolicyMapping" in this) object.inhibitPolicyMapping = this.inhibitPolicyMapping;

				return object;
			}
		}], [{
			key: 'defaultValues',
			value: function defaultValues(memberName) {
				switch (memberName) {
					case "requireExplicitPolicy":
						return 0;
					case "inhibitPolicyMapping":
						return 0;
					default:
						throw new Error('Invalid member name for PolicyConstraints class: ' + memberName);
				}
			}
		}, {
			key: 'schema',
			value: function schema() {
				var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

				var names = getParametersValue$1(parameters, "names", {});

				return new Sequence({
					name: names.blockName || "",
					value: [new Primitive({
						name: names.requireExplicitPolicy || "",
						optional: true,
						idBlock: {
							tagClass: 3,
							tagNumber: 0 }
					}), new Primitive({
						name: names.inhibitPolicyMapping || "",
						optional: true,
						idBlock: {
							tagClass: 3,
							tagNumber: 1 }
					})]
				});
			}
		}]);

		return PolicyConstraints;
	}();

	var ExtKeyUsage = function () {
		function ExtKeyUsage() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, ExtKeyUsage);

			this.keyPurposes = getParametersValue$1(parameters, "keyPurposes", ExtKeyUsage.defaultValues("keyPurposes"));

			if ("schema" in parameters) this.fromSchema(parameters.schema);
		}

		_createClass(ExtKeyUsage, [{
			key: 'fromSchema',
			value: function fromSchema(schema) {
				var asn1 = compareSchema(schema, schema, ExtKeyUsage.schema({
					names: {
						keyPurposes: "keyPurposes"
					}
				}));

				if (asn1.verified === false) throw new Error("Object's schema was not verified against input data for ExtKeyUsage");

				this.keyPurposes = Array.from(asn1.result.keyPurposes, function (element) {
					return element.valueBlock.toString();
				});
			}
		}, {
			key: 'toSchema',
			value: function toSchema() {
				return new Sequence({
					value: Array.from(this.keyPurposes, function (element) {
						return new ObjectIdentifier$1({ value: element });
					})
				});
			}
		}, {
			key: 'toJSON',
			value: function toJSON() {
				return {
					keyPurposes: Array.from(this.keyPurposes)
				};
			}
		}], [{
			key: 'defaultValues',
			value: function defaultValues(memberName) {
				switch (memberName) {
					case "keyPurposes":
						return [];
					default:
						throw new Error('Invalid member name for ExtKeyUsage class: ' + memberName);
				}
			}
		}, {
			key: 'schema',
			value: function schema() {
				var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

				var names = getParametersValue$1(parameters, "names", {});

				return new Sequence({
					name: names.blockName || "",
					value: [new Repeated({
						name: names.keyPurposes || "",
						value: new ObjectIdentifier$1()
					})]
				});
			}
		}]);

		return ExtKeyUsage;
	}();

	var InfoAccess = function () {
		function InfoAccess() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, InfoAccess);

			this.accessDescriptions = getParametersValue$1(parameters, "accessDescriptions", InfoAccess.defaultValues("accessDescriptions"));

			if ("schema" in parameters) this.fromSchema(parameters.schema);
		}

		_createClass(InfoAccess, [{
			key: 'fromSchema',
			value: function fromSchema(schema) {
				var asn1 = compareSchema(schema, schema, InfoAccess.schema({
					names: {
						accessDescriptions: "accessDescriptions"
					}
				}));

				if (asn1.verified === false) throw new Error("Object's schema was not verified against input data for InfoAccess");

				this.accessDescriptions = Array.from(asn1.result.accessDescriptions, function (element) {
					return new AccessDescription({ schema: element });
				});
			}
		}, {
			key: 'toSchema',
			value: function toSchema() {
				return new Sequence({
					value: Array.from(this.accessDescriptions, function (element) {
						return element.toSchema();
					})
				});
			}
		}, {
			key: 'toJSON',
			value: function toJSON() {
				return {
					accessDescriptions: Array.from(this.accessDescriptions, function (element) {
						return element.toJSON();
					})
				};
			}
		}], [{
			key: 'defaultValues',
			value: function defaultValues(memberName) {
				switch (memberName) {
					case "accessDescriptions":
						return [];
					default:
						throw new Error('Invalid member name for InfoAccess class: ' + memberName);
				}
			}
		}, {
			key: 'schema',
			value: function schema() {
				var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

				var names = getParametersValue$1(parameters, "names", {});

				return new Sequence({
					name: names.blockName || "",
					value: [new Repeated({
						name: names.accessDescriptions || "",
						value: AccessDescription.schema()
					})]
				});
			}
		}]);

		return InfoAccess;
	}();

	var Extension = function () {
		function Extension() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, Extension);

			this.extnID = getParametersValue$1(parameters, "extnID", Extension.defaultValues("extnID"));

			this.critical = getParametersValue$1(parameters, "critical", Extension.defaultValues("critical"));

			if ("extnValue" in parameters) this.extnValue = new OctetString({ valueHex: parameters.extnValue });else this.extnValue = Extension.defaultValues("extnValue");

			if ("parsedValue" in parameters) this.parsedValue = getParametersValue$1(parameters, "parsedValue", Extension.defaultValues("parsedValue"));

			if ("schema" in parameters) this.fromSchema(parameters.schema);
		}

		_createClass(Extension, [{
			key: 'fromSchema',
			value: function fromSchema(schema) {
				var asn1 = compareSchema(schema, schema, Extension.schema({
					names: {
						extnID: "extnID",
						critical: "critical",
						extnValue: "extnValue"
					}
				}));

				if (asn1.verified === false) throw new Error("Object's schema was not verified against input data for EXTENSION");

				this.extnID = asn1.result.extnID.valueBlock.toString();
				if ("critical" in asn1.result) this.critical = asn1.result.critical.valueBlock.value;
				this.extnValue = asn1.result.extnValue;

				asn1 = fromBER(this.extnValue.valueBlock.valueHex);
				if (asn1.offset === -1) return;

				switch (this.extnID) {
					case "2.5.29.9":
						this.parsedValue = new SubjectDirectoryAttributes({ schema: asn1.result });
						break;
					case "2.5.29.14":
						this.parsedValue = asn1.result;
						break;
					case "2.5.29.15":
						this.parsedValue = asn1.result;
						break;
					case "2.5.29.16":
						this.parsedValue = new PrivateKeyUsagePeriod({ schema: asn1.result });
						break;
					case "2.5.29.17":
					case "2.5.29.18":
						this.parsedValue = new AltName({ schema: asn1.result });
						break;
					case "2.5.29.19":
						this.parsedValue = new BasicConstraints({ schema: asn1.result });
						break;
					case "2.5.29.20":
					case "2.5.29.27":
						this.parsedValue = asn1.result;
						break;
					case "2.5.29.21":
						this.parsedValue = asn1.result;
						break;
					case "2.5.29.24":
						this.parsedValue = asn1.result;
						break;
					case "2.5.29.28":
						this.parsedValue = new IssuingDistributionPoint({ schema: asn1.result });
						break;
					case "2.5.29.29":
						this.parsedValue = new GeneralNames({ schema: asn1.result });
						break;
					case "2.5.29.30":
						this.parsedValue = new NameConstraints({ schema: asn1.result });
						break;
					case "2.5.29.31":
					case "2.5.29.46":
						this.parsedValue = new CRLDistributionPoints({ schema: asn1.result });
						break;
					case "2.5.29.32":
						this.parsedValue = new CertificatePolicies({ schema: asn1.result });
						break;
					case "2.5.29.33":
						this.parsedValue = new PolicyMappings({ schema: asn1.result });
						break;
					case "2.5.29.35":
						this.parsedValue = new AuthorityKeyIdentifier({ schema: asn1.result });
						break;
					case "2.5.29.36":
						this.parsedValue = new PolicyConstraints({ schema: asn1.result });
						break;
					case "2.5.29.37":
						this.parsedValue = new ExtKeyUsage({ schema: asn1.result });
						break;
					case "2.5.29.54":
						this.parsedValue = asn1.result;
						break;
					case "1.3.6.1.5.5.7.1.1":
					case "1.3.6.1.5.5.7.1.11":
						this.parsedValue = new InfoAccess({ schema: asn1.result });
						break;
					default:
				}
			}
		}, {
			key: 'toSchema',
			value: function toSchema() {
				var outputArray = [];

				outputArray.push(new ObjectIdentifier$1({ value: this.extnID }));

				if (this.critical !== Extension.defaultValues("critical")) outputArray.push(new Boolean({ value: this.critical }));

				outputArray.push(this.extnValue);

				return new Sequence({
					value: outputArray
				});
			}
		}, {
			key: 'toJSON',
			value: function toJSON() {
				var object = {
					extnID: this.extnID,
					extnValue: this.extnValue.toJSON()
				};

				if (this.critical !== Extension.defaultValues("critical")) object.critical = this.critical;

				if ("parsedValue" in this) object.parsedValue = this.parsedValue.toJSON();

				return object;
			}
		}], [{
			key: 'defaultValues',
			value: function defaultValues(memberName) {
				switch (memberName) {
					case "extnID":
						return "";
					case "critical":
						return false;
					case "extnValue":
						return new OctetString();
					case "parsedValue":
						return {};
					default:
						throw new Error('Invalid member name for Extension class: ' + memberName);
				}
			}
		}, {
			key: 'schema',
			value: function schema() {
				var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

				var names = getParametersValue$1(parameters, "names", {});

				return new Sequence({
					name: names.blockName || "",
					value: [new ObjectIdentifier$1({ name: names.extnID || "" }), new Boolean({
						name: names.critical || "",
						optional: true
					}), new OctetString({ name: names.extnValue || "" })]
				});
			}
		}]);

		return Extension;
	}();

	var Extensions = function () {
		function Extensions() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, Extensions);

			this.extensions = getParametersValue$1(parameters, "extensions", Extensions.defaultValues("extensions"));

			if ("schema" in parameters) this.fromSchema(parameters.schema);
		}

		_createClass(Extensions, [{
			key: 'fromSchema',
			value: function fromSchema(schema) {
				var asn1 = compareSchema(schema, schema, Extensions.schema({
					names: {
						extensions: "extensions"
					}
				}));

				if (asn1.verified === false) throw new Error("Object's schema was not verified against input data for EXTENSIONS");

				this.extensions = Array.from(asn1.result.extensions, function (element) {
					return new Extension({ schema: element });
				});
			}
		}, {
			key: 'toSchema',
			value: function toSchema() {
				return new Sequence({
					value: Array.from(this.extensions, function (element) {
						return element.toSchema();
					})
				});
			}
		}, {
			key: 'toJSON',
			value: function toJSON() {
				return {
					extensions: Array.from(this.extensions, function (element) {
						return element.toJSON();
					})
				};
			}
		}], [{
			key: 'defaultValues',
			value: function defaultValues(memberName) {
				switch (memberName) {
					case "extensions":
						return [];
					default:
						throw new Error('Invalid member name for Extensions class: ' + memberName);
				}
			}
		}, {
			key: 'schema',
			value: function schema() {
				var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};
				var optional = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : false;

				var names = getParametersValue$1(parameters, "names", {});

				return new Sequence({
					optional: optional,
					name: names.blockName || "",
					value: [new Repeated({
						name: names.extensions || "",
						value: Extension.schema(names.extension || {})
					})]
				});
			}
		}]);

		return Extensions;
	}();

	function tbsCertificate() {
		var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

		var names = getParametersValue$1(parameters, "names", {});

		return new Sequence({
			name: names.blockName || "tbsCertificate",
			value: [new Constructed({
				optional: true,
				idBlock: {
					tagClass: 3,
					tagNumber: 0 },
				value: [new Integer({ name: names.tbsCertificateVersion || "tbsCertificate.version" })]
			}), new Integer({ name: names.tbsCertificateSerialNumber || "tbsCertificate.serialNumber" }), AlgorithmIdentifier.schema(names.signature || {
				names: {
					blockName: "tbsCertificate.signature"
				}
			}), RelativeDistinguishedNames.schema(names.issuer || {
				names: {
					blockName: "tbsCertificate.issuer"
				}
			}), new Sequence({
				name: names.tbsCertificateValidity || "tbsCertificate.validity",
				value: [Time.schema(names.notBefore || {
					names: {
						utcTimeName: "tbsCertificate.notBefore",
						generalTimeName: "tbsCertificate.notBefore"
					}
				}), Time.schema(names.notAfter || {
					names: {
						utcTimeName: "tbsCertificate.notAfter",
						generalTimeName: "tbsCertificate.notAfter"
					}
				})]
			}), RelativeDistinguishedNames.schema(names.subject || {
				names: {
					blockName: "tbsCertificate.subject"
				}
			}), PublicKeyInfo.schema(names.subjectPublicKeyInfo || {
				names: {
					blockName: "tbsCertificate.subjectPublicKeyInfo"
				}
			}), new Primitive({
				name: names.tbsCertificateIssuerUniqueID || "tbsCertificate.issuerUniqueID",
				optional: true,
				idBlock: {
					tagClass: 3,
					tagNumber: 1 }
			}), new Primitive({
				name: names.tbsCertificateSubjectUniqueID || "tbsCertificate.subjectUniqueID",
				optional: true,
				idBlock: {
					tagClass: 3,
					tagNumber: 2 }
			}), new Constructed({
				optional: true,
				idBlock: {
					tagClass: 3,
					tagNumber: 3 },
				value: [Extensions.schema(names.extensions || {
					names: {
						blockName: "tbsCertificate.extensions"
					}
				})]
			})]
		});
	}

	var Certificate = function () {
		function Certificate() {
			var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

			_classCallCheck(this, Certificate);

			this.tbs = getParametersValue$1(parameters, "tbs", Certificate.defaultValues("tbs"));

			this.version = getParametersValue$1(parameters, "version", Certificate.defaultValues("version"));

			this.serialNumber = getParametersValue$1(parameters, "serialNumber", Certificate.defaultValues("serialNumber"));

			this.signature = getParametersValue$1(parameters, "signature", Certificate.defaultValues("signature"));

			this.issuer = getParametersValue$1(parameters, "issuer", Certificate.defaultValues("issuer"));

			this.notBefore = getParametersValue$1(parameters, "notBefore", Certificate.defaultValues("notBefore"));

			this.notAfter = getParametersValue$1(parameters, "notAfter", Certificate.defaultValues("notAfter"));

			this.subject = getParametersValue$1(parameters, "subject", Certificate.defaultValues("subject"));

			this.subjectPublicKeyInfo = getParametersValue$1(parameters, "subjectPublicKeyInfo", Certificate.defaultValues("subjectPublicKeyInfo"));

			if ("issuerUniqueID" in parameters) this.issuerUniqueID = getParametersValue$1(parameters, "issuerUniqueID", Certificate.defaultValues("issuerUniqueID"));

			if ("subjectUniqueID" in parameters) this.subjectUniqueID = getParametersValue$1(parameters, "subjectUniqueID", Certificate.defaultValues("subjectUniqueID"));

			if ("extensions" in parameters) this.extensions = getParametersValue$1(parameters, "extensions", Certificate.defaultValues("extensions"));

			this.signatureAlgorithm = getParametersValue$1(parameters, "signatureAlgorithm", Certificate.defaultValues("signatureAlgorithm"));

			this.signatureValue = getParametersValue$1(parameters, "signatureValue", Certificate.defaultValues("signatureValue"));

			if ("schema" in parameters) this.fromSchema(parameters.schema);
		}

		_createClass(Certificate, [{
			key: 'fromSchema',
			value: function fromSchema(schema) {
				var asn1 = compareSchema(schema, schema, Certificate.schema({
					names: {
						tbsCertificate: {
							names: {
								extensions: {
									names: {
										extensions: "tbsCertificate.extensions"
									}
								}
							}
						}
					}
				}));

				if (asn1.verified === false) throw new Error("Object's schema was not verified against input data for CERT");

				this.tbs = asn1.result.tbsCertificate.valueBeforeDecode;

				if ("tbsCertificate.version" in asn1.result) this.version = asn1.result["tbsCertificate.version"].valueBlock.valueDec;
				this.serialNumber = asn1.result["tbsCertificate.serialNumber"];
				this.signature = new AlgorithmIdentifier({ schema: asn1.result["tbsCertificate.signature"] });
				this.issuer = new RelativeDistinguishedNames({ schema: asn1.result["tbsCertificate.issuer"] });
				this.notBefore = new Time({ schema: asn1.result["tbsCertificate.notBefore"] });
				this.notAfter = new Time({ schema: asn1.result["tbsCertificate.notAfter"] });
				this.subject = new RelativeDistinguishedNames({ schema: asn1.result["tbsCertificate.subject"] });
				this.subjectPublicKeyInfo = new PublicKeyInfo({ schema: asn1.result["tbsCertificate.subjectPublicKeyInfo"] });
				if ("tbsCertificate.issuerUniqueID" in asn1.result) this.issuerUniqueID = asn1.result["tbsCertificate.issuerUniqueID"].valueBlock.valueHex;
				if ("tbsCertificate.subjectUniqueID" in asn1.result) this.issuerUniqueID = asn1.result["tbsCertificate.subjectUniqueID"].valueBlock.valueHex;
				if ("tbsCertificate.extensions" in asn1.result) this.extensions = Array.from(asn1.result["tbsCertificate.extensions"], function (element) {
					return new Extension({ schema: element });
				});

				this.signatureAlgorithm = new AlgorithmIdentifier({ schema: asn1.result.signatureAlgorithm });
				this.signatureValue = asn1.result.signatureValue;
			}
		}, {
			key: 'encodeTBS',
			value: function encodeTBS() {
				var outputArray = [];

				if ("version" in this && this.version !== Certificate.defaultValues("version")) {
					outputArray.push(new Constructed({
						optional: true,
						idBlock: {
							tagClass: 3,
							tagNumber: 0 },
						value: [new Integer({ value: this.version })]
					}));
				}

				outputArray.push(this.serialNumber);
				outputArray.push(this.signature.toSchema());
				outputArray.push(this.issuer.toSchema());

				outputArray.push(new Sequence({
					value: [this.notBefore.toSchema(), this.notAfter.toSchema()]
				}));

				outputArray.push(this.subject.toSchema());
				outputArray.push(this.subjectPublicKeyInfo.toSchema());

				if ("issuerUniqueID" in this) {
					outputArray.push(new Primitive({
						optional: true,
						idBlock: {
							tagClass: 3,
							tagNumber: 1 },
						valueHex: this.issuerUniqueID
					}));
				}
				if ("subjectUniqueID" in this) {
					outputArray.push(new Primitive({
						optional: true,
						idBlock: {
							tagClass: 3,
							tagNumber: 2 },
						valueHex: this.subjectUniqueID
					}));
				}

				if ("subjectUniqueID" in this) {
					outputArray.push(new Primitive({
						optional: true,
						idBlock: {
							tagClass: 3,
							tagNumber: 3 },
						value: [this.extensions.toSchema()]
					}));
				}

				if ("extensions" in this) {
					outputArray.push(new Constructed({
						optional: true,
						idBlock: {
							tagClass: 3,
							tagNumber: 3 },
						value: [new Sequence({
							value: Array.from(this.extensions, function (element) {
								return element.toSchema();
							})
						})]
					}));
				}

				return new Sequence({
					value: outputArray
				});
			}
		}, {
			key: 'toSchema',
			value: function toSchema() {
				var encodeFlag = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : false;

				var tbsSchema = {};

				if (encodeFlag === false) {
					if (this.tbs.length === 0) return Certificate.schema().value[0];

					tbsSchema = fromBER(this.tbs).result;
				} else tbsSchema = this.encodeTBS();

				return new Sequence({
					value: [tbsSchema, this.signatureAlgorithm.toSchema(), this.signatureValue]
				});
			}
		}, {
			key: 'toJSON',
			value: function toJSON() {
				var object = {
					tbs: bufferToHexCodes$1(this.tbs, 0, this.tbs.byteLength),
					serialNumber: this.serialNumber.toJSON(),
					signature: this.signature.toJSON(),
					issuer: this.issuer.toJSON(),
					notBefore: this.notBefore.toJSON(),
					notAfter: this.notAfter.toJSON(),
					subject: this.subject.toJSON(),
					subjectPublicKeyInfo: this.subjectPublicKeyInfo.toJSON(),
					signatureAlgorithm: this.signatureAlgorithm.toJSON(),
					signatureValue: this.signatureValue.toJSON()
				};

				if ("version" in this && this.version !== Certificate.defaultValues("version")) object.version = this.version;

				if ("issuerUniqueID" in this) object.issuerUniqueID = bufferToHexCodes$1(this.issuerUniqueID, 0, this.issuerUniqueID.byteLength);

				if ("subjectUniqueID" in this) object.subjectUniqueID = bufferToHexCodes$1(this.subjectUniqueID, 0, this.subjectUniqueID.byteLength);

				if ("extensions" in this) object.extensions = Array.from(this.extensions, function (element) {
					return element.toJSON();
				});

				return object;
			}
		}, {
			key: 'getPublicKey',
			value: function getPublicKey() {
				var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : null;

				var crypto = getCrypto();
				if (typeof crypto === "undefined") return Promise.reject("Unable to create WebCrypto object");

				if (parameters === null) {
					parameters = {};

					var shaAlgorithm = getHashAlgorithm(this.signatureAlgorithm);
					if (shaAlgorithm === "") return Promise.reject('Unsupported signature algorithm: ' + this.signatureAlgorithm.algorithmId);

					var algorithmObject = getAlgorithmByOID(this.subjectPublicKeyInfo.algorithm.algorithmId);
					if ("name" in algorithmObject === false) return Promise.reject('Unsupported public key algorithm: ' + this.subjectPublicKeyInfo.algorithm.algorithmId);

					parameters.algorithm = getAlgorithmParameters(algorithmObject.name, "importkey");
					if ("hash" in parameters.algorithm.algorithm) parameters.algorithm.algorithm.hash.name = shaAlgorithm;

					if (algorithmObject.name === "ECDSA") {
						var algorithmParamsChecked = false;

						if ("algorithmParams" in this.subjectPublicKeyInfo.algorithm === true) {
							if ("idBlock" in this.subjectPublicKeyInfo.algorithm.algorithmParams) {
								if (this.subjectPublicKeyInfo.algorithm.algorithmParams.idBlock.tagClass === 1 && this.subjectPublicKeyInfo.algorithm.algorithmParams.idBlock.tagNumber === 6) algorithmParamsChecked = true;
							}
						}

						if (algorithmParamsChecked === false) return Promise.reject("Incorrect type for ECDSA public key parameters");

						var curveObject = getAlgorithmByOID(this.subjectPublicKeyInfo.algorithm.algorithmParams.valueBlock.toString());
						if ("name" in curveObject === false) return Promise.reject('Unsupported named curve algorithm: ' + this.subjectPublicKeyInfo.algorithm.algorithmParams.valueBlock.toString());


						parameters.algorithm.algorithm.namedCurve = curveObject.name;
					}
				}

				var publicKeyInfoSchema = this.subjectPublicKeyInfo.toSchema();
				var publicKeyInfoBuffer = publicKeyInfoSchema.toBER(false);
				var publicKeyInfoView = new Uint8Array(publicKeyInfoBuffer);


				return crypto.importKey("spki", publicKeyInfoView, parameters.algorithm.algorithm, true, parameters.algorithm.usages);
			}
		}, {
			key: 'getKeyHash',
			value: function getKeyHash() {
				var crypto = getCrypto();
				if (typeof crypto === "undefined") return Promise.reject("Unable to create WebCrypto object");


				return crypto.digest({ name: "sha-1" }, new Uint8Array(this.subjectPublicKeyInfo.subjectPublicKey.valueBlock.valueHex));
			}
		}, {
			key: 'sign',
			value: function sign(privateKey) {
				var _this60 = this;

				var hashAlgorithm = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : "SHA-1";

				if (typeof privateKey === "undefined") return Promise.reject("Need to provide a private key for signing");

				var sequence = Promise.resolve();
				var parameters = void 0;

				var engine = getEngine();

				sequence = sequence.then(function () {
					return engine.subtle.getSignatureParameters(privateKey, hashAlgorithm);
				});

				sequence = sequence.then(function (result) {
					parameters = result.parameters;
					_this60.signature = result.signatureAlgorithm;
					_this60.signatureAlgorithm = result.signatureAlgorithm;
				});

				sequence = sequence.then(function () {
					_this60.tbs = _this60.encodeTBS().toBER(false);
				});

				sequence = sequence.then(function () {
					return engine.subtle.signWithPrivateKey(_this60.tbs, privateKey, parameters);
				});

				sequence = sequence.then(function (result) {
					_this60.signatureValue = new BitString({ valueHex: result });
				});


				return sequence;
			}
		}, {
			key: 'verify',
			value: function verify() {
				var _this61 = this;

				var issuerCertificate = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : null;

				var sequence = Promise.resolve();

				var subjectPublicKeyInfo = {};

				var signature = this.signatureValue;
				var tbs = this.tbs;

				if (issuerCertificate !== null) subjectPublicKeyInfo = issuerCertificate.subjectPublicKeyInfo;else {
					if (this.issuer.isEqual(this.subject)) subjectPublicKeyInfo = this.subjectPublicKeyInfo;
				}

				if (subjectPublicKeyInfo instanceof PublicKeyInfo === false) return Promise.reject("Please provide issuer certificate as a parameter");

				var crypto = getCrypto();
				if (typeof crypto === "undefined") return Promise.reject("Unable to create WebCrypto object");

				var shaAlgorithm = getHashAlgorithm(this.signatureAlgorithm);
				if (shaAlgorithm === "") return Promise.reject('Unsupported signature algorithm: ' + this.signatureAlgorithm.algorithmId);

				sequence = sequence.then(function () {
					var algorithmId = void 0;
					if (_this61.signatureAlgorithm.algorithmId === "1.2.840.113549.1.1.10") algorithmId = _this61.signatureAlgorithm.algorithmId;else algorithmId = subjectPublicKeyInfo.algorithm.algorithmId;

					var algorithmObject = getAlgorithmByOID(algorithmId);
					if ("name" in algorithmObject === false) return Promise.reject('Unsupported public key algorithm: ' + algorithmId);

					var algorithm = getAlgorithmParameters(algorithmObject.name, "importkey");
					if ("hash" in algorithm.algorithm) algorithm.algorithm.hash.name = shaAlgorithm;

					if (algorithmObject.name === "ECDSA") {
						var algorithmParamsChecked = false;

						if ("algorithmParams" in subjectPublicKeyInfo.algorithm === true) {
							if ("idBlock" in subjectPublicKeyInfo.algorithm.algorithmParams) {
								if (subjectPublicKeyInfo.algorithm.algorithmParams.idBlock.tagClass === 1 && subjectPublicKeyInfo.algorithm.algorithmParams.idBlock.tagNumber === 6) algorithmParamsChecked = true;
							}
						}

						if (algorithmParamsChecked === false) return Promise.reject("Incorrect type for ECDSA public key parameters");

						var curveObject = getAlgorithmByOID(subjectPublicKeyInfo.algorithm.algorithmParams.valueBlock.toString());
						if ("name" in curveObject === false) return Promise.reject('Unsupported named curve algorithm: ' + subjectPublicKeyInfo.algorithm.algorithmParams.valueBlock.toString());


						algorithm.algorithm.namedCurve = curveObject.name;
					}


					var publicKeyInfoSchema = subjectPublicKeyInfo.toSchema();
					var publicKeyInfoBuffer = publicKeyInfoSchema.toBER(false);
					var publicKeyInfoView = new Uint8Array(publicKeyInfoBuffer);

					return crypto.importKey("spki", publicKeyInfoView, algorithm.algorithm, true, algorithm.usages);
				});

				sequence = sequence.then(function (publicKey) {
					var algorithm = getAlgorithmParameters(publicKey.algorithm.name, "verify");
					if ("hash" in algorithm.algorithm) algorithm.algorithm.hash.name = shaAlgorithm;

					var signatureValue = signature.valueBlock.valueHex;

					if (publicKey.algorithm.name === "ECDSA") {
						var asn1 = fromBER(signatureValue);
						signatureValue = createECDSASignatureFromCMS(asn1.result);
					}

					if (publicKey.algorithm.name === "RSA-PSS") {
						var pssParameters = void 0;

						try {
							pssParameters = new RSASSAPSSParams({ schema: _this61.signatureAlgorithm.algorithmParams });
						} catch (ex) {
							return Promise.reject(ex);
						}

						if ("saltLength" in pssParameters) algorithm.algorithm.saltLength = pssParameters.saltLength;else algorithm.algorithm.saltLength = 20;

						var hashAlgo = "SHA-1";

						if ("hashAlgorithm" in pssParameters) {
							var hashAlgorithm = getAlgorithmByOID(pssParameters.hashAlgorithm.algorithmId);
							if ("name" in hashAlgorithm === false) return Promise.reject('Unrecognized hash algorithm: ' + pssParameters.hashAlgorithm.algorithmId);

							hashAlgo = hashAlgorithm.name;
						}

						algorithm.algorithm.hash.name = hashAlgo;
					}


					return crypto.verify(algorithm.algorithm, publicKey, new Uint8Array(signatureValue), new Uint8Array(tbs));
				});


				return sequence;
			}
		}], [{
			key: 'defaultValues',
			value: function defaultValues(memberName) {
				switch (memberName) {
					case "tbs":
						return new ArrayBuffer(0);
					case "version":
						return 0;
					case "serialNumber":
						return new Integer();
					case "signature":
						return new AlgorithmIdentifier();
					case "issuer":
						return new RelativeDistinguishedNames();
					case "notBefore":
						return new Time();
					case "notAfter":
						return new Time();
					case "subject":
						return new RelativeDistinguishedNames();
					case "subjectPublicKeyInfo":
						return new PublicKeyInfo();
					case "issuerUniqueID":
						return new ArrayBuffer(0);
					case "subjectUniqueID":
						return new ArrayBuffer(0);
					case "extensions":
						return [];
					case "signatureAlgorithm":
						return new AlgorithmIdentifier();
					case "signatureValue":
						return new BitString();
					default:
						throw new Error('Invalid member name for Certificate class: ' + memberName);
				}
			}
		}, {
			key: 'schema',
			value: function schema() {
				var parameters = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

				var names = getParametersValue$1(parameters, "names", {});

				return new Sequence({
					name: names.blockName || "",
					value: [tbsCertificate(names.tbsCertificate), AlgorithmIdentifier.schema(names.signatureAlgorithm || {
						names: {
							blockName: "signatureAlgorithm"
						}
					}), new BitString({ name: names.signatureValue || "signatureValue" })]
				});
			}
		}]);

		return Certificate;
	}();

	var engineCrypto = null;

	var Application = function () {
		function Application() {
			_classCallCheck(this, Application);
		}

		_createClass(Application, null, [{
			key: 'setEngine',
			value: function setEngine(name, crypto) {
				engineCrypto = {
					getRandomValues: crypto.getRandomValues.bind(crypto),
					subtle: crypto.subtle,
					name: name
				};
			}
		}, {
			key: 'isNodePlugin',
			value: function isNodePlugin() {
				return typeof self === "undefined" && typeof window === "undefined";
			}
		}, {
			key: 'crypto',
			get: function get() {
				if (!engineCrypto) {
					throw new XmlError(XE.CRYPTOGRAPHIC_NO_MODULE);
				}
				return engineCrypto;
			}
		}]);

		return Application;
	}();

	function init() {
		if (!Application.isNodePlugin()) {
			Application.setEngine("W3 WebCrypto module", self.crypto);
		}
	}
	init();

	var XmlCanonicalizerState;
	(function (XmlCanonicalizerState) {
		XmlCanonicalizerState[XmlCanonicalizerState["BeforeDocElement"] = 0] = "BeforeDocElement";
		XmlCanonicalizerState[XmlCanonicalizerState["InsideDocElement"] = 1] = "InsideDocElement";
		XmlCanonicalizerState[XmlCanonicalizerState["AfterDocElement"] = 2] = "AfterDocElement";
	})(XmlCanonicalizerState || (XmlCanonicalizerState = {}));

	var XmlCanonicalizer = function () {
		function XmlCanonicalizer(withComments, excC14N) {
			var propagatedNamespaces = arguments.length > 2 && arguments[2] !== undefined ? arguments[2] : new NamespaceManager();

			_classCallCheck(this, XmlCanonicalizer);

			this.propagatedNamespaces = new NamespaceManager();
			this.result = [];
			this.visibleNamespaces = new NamespaceManager();
			this.inclusiveNamespacesPrefixList = [];
			this.state = XmlCanonicalizerState.BeforeDocElement;
			this.withComments = withComments;
			this.exclusive = excC14N;
			this.propagatedNamespaces = propagatedNamespaces;
		}

		_createClass(XmlCanonicalizer, [{
			key: 'Canonicalize',
			value: function Canonicalize(node) {
				if (!node) {
					throw new XmlError(XE.CRYPTOGRAPHIC, "Parameter 1 is not Node");
				}
				var node2 = void 0;
				if (node.nodeType === XmlNodeType.Document) {
					this.document = node;
					node2 = this.document.documentElement;
				} else {
					this.document = node.ownerDocument;
					node2 = node;
				}

				this.WriteNode(node2);
				var res = this.result.join("");
				return res;
			}
		}, {
			key: 'WriteNode',
			value: function WriteNode(node) {
				switch (node.nodeType) {
					case XmlNodeType.Document:
					case XmlNodeType.DocumentFragment:
						this.WriteDocumentNode(node);
						break;
					case XmlNodeType.Element:
						this.WriteElementNode(node);
						break;
					case XmlNodeType.CDATA:
					case XmlNodeType.SignificantWhitespace:
					case XmlNodeType.Text:
						this.WriteTextNode(node);
						break;
					case XmlNodeType.Whitespace:
						if (this.state === XmlCanonicalizerState.InsideDocElement) {
							this.WriteTextNode(node);
						}
						break;
					case XmlNodeType.Comment:
						this.WriteCommentNode(node);
						break;
					case XmlNodeType.ProcessingInstruction:
						this.WriteProcessingInstructionNode(node);
						break;
					case XmlNodeType.EntityReference:
						for (var i = 0; i < node.childNodes.length; i++) {
							this.WriteNode(node.childNodes[i]);
						}
						break;
					case XmlNodeType.Attribute:
						throw new XmlError(XE.CRYPTOGRAPHIC, "Attribute node is impossible here");
					case XmlNodeType.EndElement:
						throw new XmlError(XE.CRYPTOGRAPHIC, "Attribute node is impossible here");
					case XmlNodeType.EndEntity:
						throw new XmlError(XE.CRYPTOGRAPHIC, "Attribute node is impossible here");
					case XmlNodeType.DocumentType:
					case XmlNodeType.Entity:
					case XmlNodeType.Notation:
					case XmlNodeType.XmlDeclaration:
						break;
				}
			}
		}, {
			key: 'WriteDocumentNode',
			value: function WriteDocumentNode(node) {
				this.state = XmlCanonicalizerState.BeforeDocElement;
				for (var child = node.firstChild; child != null; child = child.nextSibling) {
					this.WriteNode(child);
				}
			}
		}, {
			key: 'WriteCommentNode',
			value: function WriteCommentNode(node) {
				if (this.withComments) {
					if (this.state === XmlCanonicalizerState.AfterDocElement) {
						this.result.push(String.fromCharCode(10) + "<!--");
					} else {
						this.result.push("<!--");
					}
					this.result.push(this.NormalizeString(node.nodeValue, XmlNodeType.Comment));
					if (this.state === XmlCanonicalizerState.BeforeDocElement) {
						this.result.push("-->" + String.fromCharCode(10));
					} else {
						this.result.push("-->");
					}
				}
			}
		}, {
			key: 'WriteTextNode',
			value: function WriteTextNode(node) {
				this.result.push(this.NormalizeString(node.nodeValue, node.nodeType));
			}
		}, {
			key: 'WriteProcessingInstructionNode',
			value: function WriteProcessingInstructionNode(node) {
				if (this.state === XmlCanonicalizerState.AfterDocElement) {
					this.result.push('\n<?');
				} else {
					this.result.push("<?");
				}
				this.result.push(node.nodeName);
				if (node.nodeValue) {
					this.result.push(" ");
					this.result.push(this.NormalizeString(node.nodeValue, XmlNodeType.ProcessingInstruction));
				}
				if (this.state === XmlCanonicalizerState.BeforeDocElement) {
					this.result.push('?>\n');
				} else {
					this.result.push("?>");
				}
			}
		}, {
			key: 'WriteElementNode',
			value: function WriteElementNode(node) {
				if (this.state === XmlCanonicalizerState.BeforeDocElement) {
					this.state = XmlCanonicalizerState.InsideDocElement;
				}

				this.result.push("<");
				this.result.push(node.nodeName);

				var visibleNamespacesCount = this.WriteNamespacesAxis(node);

				this.WriteAttributesAxis(node);
				this.result.push(">");
				for (var n = node.firstChild; n != null; n = n.nextSibling) {
					this.WriteNode(n);
				}

				this.result.push("</");
				this.result.push(node.nodeName);
				this.result.push(">");
				if (this.state === XmlCanonicalizerState.BeforeDocElement) {
					this.state = XmlCanonicalizerState.AfterDocElement;
				}

				while (visibleNamespacesCount--) {
					this.visibleNamespaces.Pop();
				}
			}
		}, {
			key: 'WriteNamespacesAxis',
			value: function WriteNamespacesAxis(node) {
				var _this62 = this;

				var list = [];
				var visibleNamespacesCount = 0;
				for (var i = 0; i < node.attributes.length; i++) {
					var attribute = node.attributes[i];
					if (!IsNamespaceNode(attribute)) {
						if (attribute.prefix && !this.IsNamespaceRendered(attribute.prefix, attribute.namespaceURI)) {
							var ns = { prefix: attribute.prefix, namespace: attribute.namespaceURI };
							list.push(ns);
							this.visibleNamespaces.Add(ns);
							visibleNamespacesCount++;
						}
						continue;
					}
					if (attribute.localName === "xmlns" && !attribute.prefix && !attribute.nodeValue) {
						var _ns = { prefix: attribute.prefix, namespace: attribute.nodeValue };
						list.push(_ns);
						this.visibleNamespaces.Add(_ns);
						visibleNamespacesCount++;
					}

					var prefix = null;
					var matches = void 0;
					if (matches = /xmlns:([\w\.]+)/.exec(attribute.nodeName)) {
						prefix = matches[1];
					}
					var printable = true;
					if (this.exclusive && !this.IsNamespaceInclusive(node, prefix)) {
						var used = IsNamespaceUsed(node, prefix);
						if (used > 1) {
							printable = false;
						} else if (used === 0) {
							continue;
						}
					}
					if (this.IsNamespaceRendered(prefix, attribute.nodeValue)) {
						continue;
					}
					if (printable) {
						var _ns2 = { prefix: prefix, namespace: attribute.nodeValue };
						list.push(_ns2);
						this.visibleNamespaces.Add(_ns2);
						visibleNamespacesCount++;
					}
				}
				if (!this.IsNamespaceRendered(node.prefix, node.namespaceURI) && node.namespaceURI !== "http://www.w3.org/2000/xmlns/") {
					var _ns3 = { prefix: node.prefix, namespace: node.namespaceURI };
					list.push(_ns3);
					this.visibleNamespaces.Add(_ns3);
					visibleNamespacesCount++;
				}

				list.sort(XmlDsigC14NTransformNamespacesComparer);
				var prevPrefix = null;
				list.forEach(function (n) {
					if (n.prefix === prevPrefix) {
						return;
					}
					prevPrefix = n.prefix;
					_this62.result.push(" xmlns");
					if (n.prefix) {
						_this62.result.push(":" + n.prefix);
					}
					_this62.result.push("=\"");
					_this62.result.push(n.namespace);
					_this62.result.push("\"");
				});
				return visibleNamespacesCount;
			}
		}, {
			key: 'WriteAttributesAxis',
			value: function WriteAttributesAxis(node) {
				var _this63 = this;

				var list = [];
				for (var i = 0; i < node.attributes.length; i++) {
					var attribute = node.attributes[i];
					if (!IsNamespaceNode(attribute)) {
						list.push(attribute);
					}
				}

				list.sort(XmlDsigC14NTransformAttributesComparer);
				list.forEach(function (attribute) {
					if (attribute != null) {
						_this63.result.push(" ");
						_this63.result.push(attribute.nodeName);
						_this63.result.push("=\"");
						_this63.result.push(_this63.NormalizeString(attribute.nodeValue, XmlNodeType.Attribute));
						_this63.result.push("\"");
					}
				});
			}
		}, {
			key: 'NormalizeString',
			value: function NormalizeString(input, type) {
				var sb = [];
				if (input) {
					for (var i = 0; i < input.length; i++) {
						var ch = input[i];
						if (ch === "<" && (type === XmlNodeType.Attribute || this.IsTextNode(type))) {
							sb.push("&lt;");
						} else if (ch === ">" && this.IsTextNode(type)) {
							sb.push("&gt;");
						} else if (ch === "&" && (type === XmlNodeType.Attribute || this.IsTextNode(type))) {
							sb.push("&amp;");
						} else if (ch === "\"" && type === XmlNodeType.Attribute) {
							sb.push("&quot;");
						} else if (ch === '\t' && type === XmlNodeType.Attribute) {
							sb.push("&#x9;");
						} else if (ch === '\n' && type === XmlNodeType.Attribute) {
							sb.push("&#xA;");
						} else if (ch === '\r') {
							sb.push("&#xD;");
						} else {
							sb.push(ch);
						}
					}
				}
				return sb.join("");
			}
		}, {
			key: 'IsTextNode',
			value: function IsTextNode(type) {
				switch (type) {
					case XmlNodeType.Text:
					case XmlNodeType.CDATA:
					case XmlNodeType.SignificantWhitespace:
					case XmlNodeType.Whitespace:
						return true;
				}
				return false;
			}
		}, {
			key: 'IsNamespaceInclusive',
			value: function IsNamespaceInclusive(node, prefix) {
				var prefix2 = prefix || null;
				if (node.prefix === prefix2) {
					return false;
				}
				return this.inclusiveNamespacesPrefixList.indexOf(prefix2 || "") !== -1;
			}
		}, {
			key: 'IsNamespaceRendered',
			value: function IsNamespaceRendered(prefix, uri) {
				prefix = prefix || "";
				uri = uri || "";
				if (!prefix && !uri) {
					return true;
				}
				if (prefix === "xml" && uri === "http://www.w3.org/XML/1998/namespace") {
					return true;
				}
				var ns = this.visibleNamespaces.GetPrefix(prefix);
				if (ns) {
					return ns.namespace === uri;
				}
				return false;
			}
		}, {
			key: 'InclusiveNamespacesPrefixList',
			get: function get() {
				return this.inclusiveNamespacesPrefixList.join(" ");
			},
			set: function set(value) {
				this.inclusiveNamespacesPrefixList = value.split(" ");
			}
		}]);

		return XmlCanonicalizer;
	}();

	function XmlDsigC14NTransformNamespacesComparer(x, y) {
		if (x == y) {
			return 0;
		} else if (!x) {
			return -1;
		} else if (!y) {
			return 1;
		} else if (!x.prefix) {
			return -1;
		} else if (!y.prefix) {
			return 1;
		}
		return x.prefix.localeCompare(y.prefix);
	}
	function XmlDsigC14NTransformAttributesComparer(x, y) {
		if (!x.namespaceURI && y.namespaceURI) {
			return -1;
		}
		if (!y.namespaceURI && x.namespaceURI) {
			return 1;
		}
		var left = x.namespaceURI + x.localName;
		var right = y.namespaceURI + y.localName;
		if (left === right) {
			return 0;
		} else if (left < right) {
			return -1;
		} else {
			return 1;
		}
	}
	function IsNamespaceUsed(node, prefix) {
		var result = arguments.length > 2 && arguments[2] !== undefined ? arguments[2] : 0;

		var prefix2 = prefix || null;
		if (node.prefix === prefix2) {
			return ++result;
		}

		if (node.attributes) {
			for (var i = 0; i < node.attributes.length; i++) {
				var attr = node.attributes[i];
				if (!IsNamespaceNode(attr) && prefix && node.attributes[i].prefix === prefix) {
					return ++result;
				}
			}
		}

		for (var n = node.firstChild; !!n; n = n.nextSibling) {
			if (n.nodeType === XmlNodeType.Element) {
				var el = n;
				var res = IsNamespaceUsed(el, prefix, result);
				if (n.nodeType === XmlNodeType.Element && res) {
					return ++result + res;
				}
			}
		}
		return result;
	}
	function IsNamespaceNode(node) {
		var reg = /xmlns:/;
		if (node !== null && node.nodeType === XmlNodeType.Attribute && (node.nodeName === "xmlns" || reg.test(node.nodeName))) {
			return true;
		}
		return false;
	}

	var XmlSignature = {
		DefaultCanonMethod: "http://www.w3.org/TR/2001/REC-xml-c14n-20010315",
		DefaultDigestMethod: "http://www.w3.org/2001/04/xmlenc#sha256",
		DefaultPrefix: "ds",
		ElementNames: {
			CanonicalizationMethod: "CanonicalizationMethod",
			DigestMethod: "DigestMethod",
			DigestValue: "DigestValue",
			DSAKeyValue: "DSAKeyValue",
			DomainParameters: "DomainParameters",
			EncryptedKey: "EncryptedKey",
			HMACOutputLength: "HMACOutputLength",
			RSAPSSParams: "RSAPSSParams",
			MaskGenerationFunction: "MaskGenerationFunction",
			SaltLength: "SaltLength",
			KeyInfo: "KeyInfo",
			KeyName: "KeyName",
			KeyValue: "KeyValue",
			Modulus: "Modulus",
			Exponent: "Exponent",
			Manifest: "Manifest",
			Object: "Object",
			Reference: "Reference",
			RetrievalMethod: "RetrievalMethod",
			RSAKeyValue: "RSAKeyValue",
			ECDSAKeyValue: "ECDSAKeyValue",
			NamedCurve: "NamedCurve",
			PublicKey: "PublicKey",
			Signature: "Signature",
			SignatureMethod: "SignatureMethod",
			SignatureValue: "SignatureValue",
			SignedInfo: "SignedInfo",
			Transform: "Transform",
			Transforms: "Transforms",
			X509Data: "X509Data",
			PGPData: "PGPData",
			SPKIData: "SPKIData",
			SPKIexp: "SPKIexp",
			MgmtData: "MgmtData",
			X509IssuerSerial: "X509IssuerSerial",
			X509IssuerName: "X509IssuerName",
			X509SerialNumber: "X509SerialNumber",
			X509SKI: "X509SKI",
			X509SubjectName: "X509SubjectName",
			X509Certificate: "X509Certificate",
			X509CRL: "X509CRL",
			XPath: "XPath",
			X: "X",
			Y: "Y"
		},
		AttributeNames: {
			Algorithm: "Algorithm",
			Encoding: "Encoding",
			Id: "Id",
			MimeType: "MimeType",
			Type: "Type",
			URI: "URI"
		},
		AlgorithmNamespaces: {
			XmlDsigBase64Transform: "http://www.w3.org/2000/09/xmldsig#base64",
			XmlDsigC14NTransform: "http://www.w3.org/TR/2001/REC-xml-c14n-20010315",
			XmlDsigC14NWithCommentsTransform: "http://www.w3.org/TR/2001/REC-xml-c14n-20010315#WithComments",
			XmlDsigEnvelopedSignatureTransform: "http://www.w3.org/2000/09/xmldsig#enveloped-signature",
			XmlDsigXPathTransform: "http://www.w3.org/TR/1999/REC-xpath-19991116",
			XmlDsigXsltTransform: "http://www.w3.org/TR/1999/REC-xslt-19991116",
			XmlDsigExcC14NTransform: "http://www.w3.org/2001/10/xml-exc-c14n#",
			XmlDsigExcC14NWithCommentsTransform: "http://www.w3.org/2001/10/xml-exc-c14n#WithComments",
			XmlDecryptionTransform: "http://www.w3.org/2002/07/decrypt#XML",
			XmlLicenseTransform: "urn:mpeg:mpeg21:2003:01-REL-R-NS:licenseTransform"
		},
		Uri: {
			Manifest: "http://www.w3.org/2000/09/xmldsig#Manifest"
		},
		NamespaceURI: "http://www.w3.org/2000/09/xmldsig#",
		NamespaceURIMore: "http://www.w3.org/2007/05/xmldsig-more#",
		NamespaceURIPss: "http://www.example.org/xmldsig-pss/#"
	};

	var XmlSignatureObject = function (_XmlObject2) {
		_inherits(XmlSignatureObject, _XmlObject2);

		function XmlSignatureObject() {
			_classCallCheck(this, XmlSignatureObject);

			return _possibleConstructorReturn(this, (XmlSignatureObject.__proto__ || Object.getPrototypeOf(XmlSignatureObject)).apply(this, arguments));
		}

		return XmlSignatureObject;
	}(XmlObject);
	XmlSignatureObject = __decorate([XmlElement({
		localName: "xmldsig",
		namespaceURI: XmlSignature.NamespaceURI,
		prefix: XmlSignature.DefaultPrefix
	})], XmlSignatureObject);
	var XmlSignatureCollection = function (_XmlCollection) {
		_inherits(XmlSignatureCollection, _XmlCollection);

		function XmlSignatureCollection() {
			_classCallCheck(this, XmlSignatureCollection);

			return _possibleConstructorReturn(this, (XmlSignatureCollection.__proto__ || Object.getPrototypeOf(XmlSignatureCollection)).apply(this, arguments));
		}

		return XmlSignatureCollection;
	}(XmlCollection);
	XmlSignatureCollection = __decorate([XmlElement({
		localName: "xmldsig_collection",
		namespaceURI: XmlSignature.NamespaceURI,
		prefix: XmlSignature.DefaultPrefix
	})], XmlSignatureCollection);

	var KeyInfoClause = function (_XmlSignatureObject) {
		_inherits(KeyInfoClause, _XmlSignatureObject);

		function KeyInfoClause() {
			_classCallCheck(this, KeyInfoClause);

			return _possibleConstructorReturn(this, (KeyInfoClause.__proto__ || Object.getPrototypeOf(KeyInfoClause)).apply(this, arguments));
		}

		return KeyInfoClause;
	}(XmlSignatureObject);

	var XmlAlgorithm = function () {
		function XmlAlgorithm() {
			_classCallCheck(this, XmlAlgorithm);
		}

		_createClass(XmlAlgorithm, [{
			key: 'getAlgorithmName',
			value: function getAlgorithmName() {
				return this.namespaceURI;
			}
		}]);

		return XmlAlgorithm;
	}();

	var HashAlgorithm = function (_XmlAlgorithm) {
		_inherits(HashAlgorithm, _XmlAlgorithm);

		function HashAlgorithm() {
			_classCallCheck(this, HashAlgorithm);

			return _possibleConstructorReturn(this, (HashAlgorithm.__proto__ || Object.getPrototypeOf(HashAlgorithm)).apply(this, arguments));
		}

		_createClass(HashAlgorithm, [{
			key: 'Digest',
			value: function Digest(xml) {
				var _this68 = this;

				return Promise.resolve().then(function () {
					var buf = void 0;
					if (typeof xml === "string") {
						buf = Convert.FromString(xml, "utf8");
					} else if (ArrayBuffer.isView(xml) || xml instanceof ArrayBuffer) {
						buf = xml;
					} else {
						var txt = new XMLSerializer().serializeToString(xml);
						buf = Convert.FromString(txt, "utf8");
					}
					return Application.crypto.subtle.digest(_this68.algorithm, buf);
				}).then(function (hash) {
					return new Uint8Array(hash);
				});
			}
		}]);

		return HashAlgorithm;
	}(XmlAlgorithm);

	var SignatureAlgorithm = function (_XmlAlgorithm2) {
		_inherits(SignatureAlgorithm, _XmlAlgorithm2);

		function SignatureAlgorithm() {
			_classCallCheck(this, SignatureAlgorithm);

			return _possibleConstructorReturn(this, (SignatureAlgorithm.__proto__ || Object.getPrototypeOf(SignatureAlgorithm)).apply(this, arguments));
		}

		_createClass(SignatureAlgorithm, [{
			key: 'Sign',
			value: function Sign(signedInfo, signingKey, algorithm) {
				var info = Convert.FromString(signedInfo, "utf8");
				return Application.crypto.subtle.sign(algorithm, signingKey, info);
			}
		}, {
			key: 'Verify',
			value: function Verify(signedInfo, key, signatureValue, algorithm) {
				var info = Convert.FromString(signedInfo, "utf8");
				return Application.crypto.subtle.verify(algorithm || this.algorithm, key, signatureValue, info);
			}
		}]);

		return SignatureAlgorithm;
	}(XmlAlgorithm);

	var SHA1 = "SHA-1";
	var SHA256 = "SHA-256";
	var SHA384 = "SHA-384";
	var SHA512 = "SHA-512";
	var SHA1_NAMESPACE = "http://www.w3.org/2000/09/xmldsig#sha1";
	var SHA256_NAMESPACE = "http://www.w3.org/2001/04/xmlenc#sha256";
	var SHA384_NAMESPACE = "http://www.w3.org/2001/04/xmldsig-more#sha384";
	var SHA512_NAMESPACE = "http://www.w3.org/2001/04/xmlenc#sha512";

	var Sha1 = function (_HashAlgorithm) {
		_inherits(Sha1, _HashAlgorithm);

		function Sha1() {
			_classCallCheck(this, Sha1);

			var _this70 = _possibleConstructorReturn(this, (Sha1.__proto__ || Object.getPrototypeOf(Sha1)).apply(this, arguments));

			_this70.algorithm = { name: SHA1 };
			_this70.namespaceURI = SHA1_NAMESPACE;
			return _this70;
		}

		return Sha1;
	}(HashAlgorithm);

	var Sha256 = function (_HashAlgorithm2) {
		_inherits(Sha256, _HashAlgorithm2);

		function Sha256() {
			_classCallCheck(this, Sha256);

			var _this71 = _possibleConstructorReturn(this, (Sha256.__proto__ || Object.getPrototypeOf(Sha256)).apply(this, arguments));

			_this71.algorithm = { name: SHA256 };
			_this71.namespaceURI = SHA256_NAMESPACE;
			return _this71;
		}

		return Sha256;
	}(HashAlgorithm);

	var Sha384 = function (_HashAlgorithm3) {
		_inherits(Sha384, _HashAlgorithm3);

		function Sha384() {
			_classCallCheck(this, Sha384);

			var _this72 = _possibleConstructorReturn(this, (Sha384.__proto__ || Object.getPrototypeOf(Sha384)).apply(this, arguments));

			_this72.algorithm = { name: SHA384 };
			_this72.namespaceURI = SHA384_NAMESPACE;
			return _this72;
		}

		return Sha384;
	}(HashAlgorithm);

	var Sha512 = function (_HashAlgorithm4) {
		_inherits(Sha512, _HashAlgorithm4);

		function Sha512() {
			_classCallCheck(this, Sha512);

			var _this73 = _possibleConstructorReturn(this, (Sha512.__proto__ || Object.getPrototypeOf(Sha512)).apply(this, arguments));

			_this73.algorithm = { name: SHA512 };
			_this73.namespaceURI = SHA512_NAMESPACE;
			return _this73;
		}

		return Sha512;
	}(HashAlgorithm);

	var ECDSA = "ECDSA";
	var ECDSA_SHA1_NAMESPACE = "http://www.w3.org/2001/04/xmldsig-more#ecdsa-sha1";
	var ECDSA_SHA256_NAMESPACE = "http://www.w3.org/2001/04/xmldsig-more#ecdsa-sha256";
	var ECDSA_SHA384_NAMESPACE = "http://www.w3.org/2001/04/xmldsig-more#ecdsa-sha384";
	var ECDSA_SHA512_NAMESPACE = "http://www.w3.org/2001/04/xmldsig-more#ecdsa-sha512";

	var EcdsaSha1 = function (_SignatureAlgorithm) {
		_inherits(EcdsaSha1, _SignatureAlgorithm);

		function EcdsaSha1() {
			_classCallCheck(this, EcdsaSha1);

			var _this74 = _possibleConstructorReturn(this, (EcdsaSha1.__proto__ || Object.getPrototypeOf(EcdsaSha1)).apply(this, arguments));

			_this74.algorithm = {
				name: ECDSA,
				hash: {
					name: SHA1
				}
			};
			_this74.namespaceURI = ECDSA_SHA1_NAMESPACE;
			return _this74;
		}

		return EcdsaSha1;
	}(SignatureAlgorithm);

	var EcdsaSha256 = function (_SignatureAlgorithm2) {
		_inherits(EcdsaSha256, _SignatureAlgorithm2);

		function EcdsaSha256() {
			_classCallCheck(this, EcdsaSha256);

			var _this75 = _possibleConstructorReturn(this, (EcdsaSha256.__proto__ || Object.getPrototypeOf(EcdsaSha256)).apply(this, arguments));

			_this75.algorithm = {
				name: ECDSA,
				hash: {
					name: SHA256
				}
			};
			_this75.namespaceURI = ECDSA_SHA256_NAMESPACE;
			return _this75;
		}

		return EcdsaSha256;
	}(SignatureAlgorithm);

	var EcdsaSha384 = function (_SignatureAlgorithm3) {
		_inherits(EcdsaSha384, _SignatureAlgorithm3);

		function EcdsaSha384() {
			_classCallCheck(this, EcdsaSha384);

			var _this76 = _possibleConstructorReturn(this, (EcdsaSha384.__proto__ || Object.getPrototypeOf(EcdsaSha384)).apply(this, arguments));

			_this76.algorithm = {
				name: ECDSA,
				hash: {
					name: SHA384
				}
			};
			_this76.namespaceURI = ECDSA_SHA384_NAMESPACE;
			return _this76;
		}

		return EcdsaSha384;
	}(SignatureAlgorithm);

	var EcdsaSha512 = function (_SignatureAlgorithm4) {
		_inherits(EcdsaSha512, _SignatureAlgorithm4);

		function EcdsaSha512() {
			_classCallCheck(this, EcdsaSha512);

			var _this77 = _possibleConstructorReturn(this, (EcdsaSha512.__proto__ || Object.getPrototypeOf(EcdsaSha512)).apply(this, arguments));

			_this77.algorithm = {
				name: ECDSA,
				hash: {
					name: SHA512
				}
			};
			_this77.namespaceURI = ECDSA_SHA512_NAMESPACE;
			return _this77;
		}

		return EcdsaSha512;
	}(SignatureAlgorithm);

	var HMAC = "HMAC";
	var HMAC_SHA1_NAMESPACE = "http://www.w3.org/2000/09/xmldsig#hmac-sha1";
	var HMAC_SHA256_NAMESPACE = "http://www.w3.org/2001/04/xmldsig-more#hmac-sha256";
	var HMAC_SHA384_NAMESPACE = "http://www.w3.org/2001/04/xmldsig-more#hmac-sha384";
	var HMAC_SHA512_NAMESPACE = "http://www.w3.org/2001/04/xmldsig-more#hmac-sha512";

	var HmacSha1 = function (_SignatureAlgorithm5) {
		_inherits(HmacSha1, _SignatureAlgorithm5);

		function HmacSha1() {
			_classCallCheck(this, HmacSha1);

			var _this78 = _possibleConstructorReturn(this, (HmacSha1.__proto__ || Object.getPrototypeOf(HmacSha1)).apply(this, arguments));

			_this78.algorithm = {
				name: HMAC,
				hash: {
					name: SHA1
				}
			};
			_this78.namespaceURI = HMAC_SHA1_NAMESPACE;
			return _this78;
		}

		return HmacSha1;
	}(SignatureAlgorithm);

	var HmacSha256 = function (_SignatureAlgorithm6) {
		_inherits(HmacSha256, _SignatureAlgorithm6);

		function HmacSha256() {
			_classCallCheck(this, HmacSha256);

			var _this79 = _possibleConstructorReturn(this, (HmacSha256.__proto__ || Object.getPrototypeOf(HmacSha256)).apply(this, arguments));

			_this79.algorithm = {
				name: HMAC,
				hash: {
					name: SHA256
				}
			};
			_this79.namespaceURI = HMAC_SHA256_NAMESPACE;
			return _this79;
		}

		return HmacSha256;
	}(SignatureAlgorithm);

	var HmacSha384 = function (_SignatureAlgorithm7) {
		_inherits(HmacSha384, _SignatureAlgorithm7);

		function HmacSha384() {
			_classCallCheck(this, HmacSha384);

			var _this80 = _possibleConstructorReturn(this, (HmacSha384.__proto__ || Object.getPrototypeOf(HmacSha384)).apply(this, arguments));

			_this80.algorithm = {
				name: HMAC,
				hash: {
					name: SHA384
				}
			};
			_this80.namespaceURI = HMAC_SHA384_NAMESPACE;
			return _this80;
		}

		return HmacSha384;
	}(SignatureAlgorithm);

	var HmacSha512 = function (_SignatureAlgorithm8) {
		_inherits(HmacSha512, _SignatureAlgorithm8);

		function HmacSha512() {
			_classCallCheck(this, HmacSha512);

			var _this81 = _possibleConstructorReturn(this, (HmacSha512.__proto__ || Object.getPrototypeOf(HmacSha512)).apply(this, arguments));

			_this81.algorithm = {
				name: HMAC,
				hash: {
					name: SHA512
				}
			};
			_this81.namespaceURI = HMAC_SHA512_NAMESPACE;
			return _this81;
		}

		return HmacSha512;
	}(SignatureAlgorithm);

	var RSA_PKCS1 = "RSASSA-PKCS1-v1_5";
	var RSA_PKCS1_SHA1_NAMESPACE = "http://www.w3.org/2000/09/xmldsig#rsa-sha1";
	var RSA_PKCS1_SHA256_NAMESPACE = "http://www.w3.org/2001/04/xmldsig-more#rsa-sha256";
	var RSA_PKCS1_SHA384_NAMESPACE = "http://www.w3.org/2001/04/xmldsig-more#rsa-sha384";
	var RSA_PKCS1_SHA512_NAMESPACE = "http://www.w3.org/2001/04/xmldsig-more#rsa-sha512";

	var RsaPkcs1Sha1 = function (_SignatureAlgorithm9) {
		_inherits(RsaPkcs1Sha1, _SignatureAlgorithm9);

		function RsaPkcs1Sha1() {
			_classCallCheck(this, RsaPkcs1Sha1);

			var _this82 = _possibleConstructorReturn(this, (RsaPkcs1Sha1.__proto__ || Object.getPrototypeOf(RsaPkcs1Sha1)).apply(this, arguments));

			_this82.algorithm = {
				name: RSA_PKCS1,
				hash: {
					name: SHA1
				}
			};
			_this82.namespaceURI = RSA_PKCS1_SHA1_NAMESPACE;
			return _this82;
		}

		return RsaPkcs1Sha1;
	}(SignatureAlgorithm);

	var RsaPkcs1Sha256 = function (_SignatureAlgorithm10) {
		_inherits(RsaPkcs1Sha256, _SignatureAlgorithm10);

		function RsaPkcs1Sha256() {
			_classCallCheck(this, RsaPkcs1Sha256);

			var _this83 = _possibleConstructorReturn(this, (RsaPkcs1Sha256.__proto__ || Object.getPrototypeOf(RsaPkcs1Sha256)).apply(this, arguments));

			_this83.algorithm = {
				name: RSA_PKCS1,
				hash: {
					name: SHA256
				}
			};
			_this83.namespaceURI = RSA_PKCS1_SHA256_NAMESPACE;
			return _this83;
		}

		return RsaPkcs1Sha256;
	}(SignatureAlgorithm);

	var RsaPkcs1Sha384 = function (_SignatureAlgorithm11) {
		_inherits(RsaPkcs1Sha384, _SignatureAlgorithm11);

		function RsaPkcs1Sha384() {
			_classCallCheck(this, RsaPkcs1Sha384);

			var _this84 = _possibleConstructorReturn(this, (RsaPkcs1Sha384.__proto__ || Object.getPrototypeOf(RsaPkcs1Sha384)).apply(this, arguments));

			_this84.algorithm = {
				name: RSA_PKCS1,
				hash: {
					name: SHA384
				}
			};
			_this84.namespaceURI = RSA_PKCS1_SHA384_NAMESPACE;
			return _this84;
		}

		return RsaPkcs1Sha384;
	}(SignatureAlgorithm);

	var RsaPkcs1Sha512 = function (_SignatureAlgorithm12) {
		_inherits(RsaPkcs1Sha512, _SignatureAlgorithm12);

		function RsaPkcs1Sha512() {
			_classCallCheck(this, RsaPkcs1Sha512);

			var _this85 = _possibleConstructorReturn(this, (RsaPkcs1Sha512.__proto__ || Object.getPrototypeOf(RsaPkcs1Sha512)).apply(this, arguments));

			_this85.algorithm = {
				name: RSA_PKCS1,
				hash: {
					name: SHA512
				}
			};
			_this85.namespaceURI = RSA_PKCS1_SHA512_NAMESPACE;
			return _this85;
		}

		return RsaPkcs1Sha512;
	}(SignatureAlgorithm);

	var RSA_PSS = "RSA-PSS";
	var RSA_PSS_WITH_PARAMS_NAMESPACE = "http://www.w3.org/2007/05/xmldsig-more#rsa-pss";

	var RsaPssBase = function (_SignatureAlgorithm13) {
		_inherits(RsaPssBase, _SignatureAlgorithm13);

		function RsaPssBase(saltLength) {
			_classCallCheck(this, RsaPssBase);

			var _this86 = _possibleConstructorReturn(this, (RsaPssBase.__proto__ || Object.getPrototypeOf(RsaPssBase)).call(this));

			_this86.algorithm = {
				name: RSA_PSS,
				hash: {
					name: SHA1
				}
			};
			_this86.namespaceURI = RSA_PSS_WITH_PARAMS_NAMESPACE;
			if (saltLength) {
				_this86.algorithm.saltLength = saltLength;
			}
			return _this86;
		}

		return RsaPssBase;
	}(SignatureAlgorithm);

	var RsaPssSha1 = function (_RsaPssBase) {
		_inherits(RsaPssSha1, _RsaPssBase);

		function RsaPssSha1(saltLength) {
			_classCallCheck(this, RsaPssSha1);

			var _this87 = _possibleConstructorReturn(this, (RsaPssSha1.__proto__ || Object.getPrototypeOf(RsaPssSha1)).call(this, saltLength));

			_this87.algorithm.hash.name = SHA1;
			return _this87;
		}

		return RsaPssSha1;
	}(RsaPssBase);

	var RsaPssSha256 = function (_RsaPssBase2) {
		_inherits(RsaPssSha256, _RsaPssBase2);

		function RsaPssSha256(saltLength) {
			_classCallCheck(this, RsaPssSha256);

			var _this88 = _possibleConstructorReturn(this, (RsaPssSha256.__proto__ || Object.getPrototypeOf(RsaPssSha256)).call(this, saltLength));

			_this88.algorithm.hash.name = SHA256;
			return _this88;
		}

		return RsaPssSha256;
	}(RsaPssBase);

	var RsaPssSha384 = function (_RsaPssBase3) {
		_inherits(RsaPssSha384, _RsaPssBase3);

		function RsaPssSha384(saltLength) {
			_classCallCheck(this, RsaPssSha384);

			var _this89 = _possibleConstructorReturn(this, (RsaPssSha384.__proto__ || Object.getPrototypeOf(RsaPssSha384)).call(this, saltLength));

			_this89.algorithm.hash.name = SHA384;
			return _this89;
		}

		return RsaPssSha384;
	}(RsaPssBase);

	var RsaPssSha512 = function (_RsaPssBase4) {
		_inherits(RsaPssSha512, _RsaPssBase4);

		function RsaPssSha512(saltLength) {
			_classCallCheck(this, RsaPssSha512);

			var _this90 = _possibleConstructorReturn(this, (RsaPssSha512.__proto__ || Object.getPrototypeOf(RsaPssSha512)).call(this, saltLength));

			_this90.algorithm.hash.name = SHA512;
			return _this90;
		}

		return RsaPssSha512;
	}(RsaPssBase);

	var CanonicalizationMethod = function (_XmlSignatureObject2) {
		_inherits(CanonicalizationMethod, _XmlSignatureObject2);

		function CanonicalizationMethod() {
			_classCallCheck(this, CanonicalizationMethod);

			return _possibleConstructorReturn(this, (CanonicalizationMethod.__proto__ || Object.getPrototypeOf(CanonicalizationMethod)).apply(this, arguments));
		}

		return CanonicalizationMethod;
	}(XmlSignatureObject);
	__decorate([XmlAttribute({
		localName: XmlSignature.AttributeNames.Algorithm,
		required: true,
		defaultValue: XmlSignature.DefaultCanonMethod
	})], CanonicalizationMethod.prototype, "Algorithm", void 0);
	CanonicalizationMethod = __decorate([XmlElement({
		localName: XmlSignature.ElementNames.CanonicalizationMethod
	})], CanonicalizationMethod);

	var DataObject = function (_XmlSignatureObject3) {
		_inherits(DataObject, _XmlSignatureObject3);

		function DataObject() {
			_classCallCheck(this, DataObject);

			return _possibleConstructorReturn(this, (DataObject.__proto__ || Object.getPrototypeOf(DataObject)).apply(this, arguments));
		}

		return DataObject;
	}(XmlSignatureObject);
	__decorate([XmlAttribute({
		localName: XmlSignature.AttributeNames.Id,
		defaultValue: ""
	})], DataObject.prototype, "Id", void 0);
	__decorate([XmlAttribute({
		localName: XmlSignature.AttributeNames.MimeType,
		defaultValue: ""
	})], DataObject.prototype, "MimeType", void 0);
	__decorate([XmlAttribute({
		localName: XmlSignature.AttributeNames.Encoding,
		defaultValue: ""
	})], DataObject.prototype, "Encoding", void 0);
	DataObject = __decorate([XmlElement({
		localName: XmlSignature.ElementNames.Object
	})], DataObject);
	var DataObjects = function (_XmlSignatureCollecti) {
		_inherits(DataObjects, _XmlSignatureCollecti);

		function DataObjects() {
			_classCallCheck(this, DataObjects);

			return _possibleConstructorReturn(this, (DataObjects.__proto__ || Object.getPrototypeOf(DataObjects)).apply(this, arguments));
		}

		return DataObjects;
	}(XmlSignatureCollection);
	DataObjects = __decorate([XmlElement({
		localName: "xmldsig_objects",
		parser: DataObject
	})], DataObjects);

	var DigestMethod = function (_XmlSignatureObject4) {
		_inherits(DigestMethod, _XmlSignatureObject4);

		function DigestMethod() {
			_classCallCheck(this, DigestMethod);

			return _possibleConstructorReturn(this, (DigestMethod.__proto__ || Object.getPrototypeOf(DigestMethod)).apply(this, arguments));
		}

		return DigestMethod;
	}(XmlSignatureObject);
	__decorate([XmlAttribute({
		localName: XmlSignature.AttributeNames.Algorithm,
		required: true,
		defaultValue: XmlSignature.DefaultDigestMethod
	})], DigestMethod.prototype, "Algorithm", void 0);
	DigestMethod = __decorate([XmlElement({
		localName: XmlSignature.ElementNames.DigestMethod
	})], DigestMethod);

	var KeyInfo = function (_XmlSignatureCollecti2) {
		_inherits(KeyInfo, _XmlSignatureCollecti2);

		function KeyInfo() {
			_classCallCheck(this, KeyInfo);

			return _possibleConstructorReturn(this, (KeyInfo.__proto__ || Object.getPrototypeOf(KeyInfo)).apply(this, arguments));
		}

		_createClass(KeyInfo, [{
			key: 'OnLoadXml',
			value: function OnLoadXml(element) {
				var _this96 = this;

				var _loop = function _loop(i) {
					var node = element.childNodes.item(i);
					if (node.nodeType !== XmlNodeType.Element) {
						return 'continue';
					}
					var KeyInfoClass = null;
					switch (node.localName) {
						case XmlSignature.ElementNames.KeyValue:
							KeyInfoClass = KeyValue;
							break;
						case XmlSignature.ElementNames.X509Data:
							KeyInfoClass = KeyInfoX509Data;
							break;
						case XmlSignature.ElementNames.SPKIData:
							KeyInfoClass = SPKIData;
							break;
						case XmlSignature.ElementNames.KeyName:
						case XmlSignature.ElementNames.RetrievalMethod:
						case XmlSignature.ElementNames.PGPData:
						case XmlSignature.ElementNames.MgmtData:
					}
					if (KeyInfoClass) {
						var item = new KeyInfoClass();
						item.LoadXml(node);
						if (item instanceof KeyValue) {
							var keyValue = null;
							[RsaKeyValue, EcdsaKeyValue].some(function (KeyClass) {
								try {
									var k = new KeyClass();
									for (var j = 0; j < node.childNodes.length; j++) {
										var nodeKey = node.childNodes.item(j);
										if (nodeKey.nodeType !== XmlNodeType.Element) {
											continue;
										}
										k.LoadXml(nodeKey);
										keyValue = k;
										return true;
									}
								} catch (_a) {}
								return false;
							});
							if (keyValue) {
								item.Value = keyValue;
							} else {
								throw new XmlError(XE.CRYPTOGRAPHIC, "Unsupported KeyValue in use");
							}
						}
						_this96.Add(item);
					}
				};

				for (var i = 0; i < element.childNodes.length; i++) {
					var _ret = _loop(i);

					if (_ret === 'continue') continue;
				}
			}
		}]);

		return KeyInfo;
	}(XmlSignatureCollection);
	__decorate([XmlAttribute({
		localName: XmlSignature.AttributeNames.Id,
		defaultValue: ""
	})], KeyInfo.prototype, "Id", void 0);
	KeyInfo = __decorate([XmlElement({
		localName: XmlSignature.ElementNames.KeyInfo
	})], KeyInfo);

	var Transform = function (_XmlSignatureObject5) {
		_inherits(Transform, _XmlSignatureObject5);

		function Transform() {
			_classCallCheck(this, Transform);

			var _this97 = _possibleConstructorReturn(this, (Transform.__proto__ || Object.getPrototypeOf(Transform)).apply(this, arguments));

			_this97.innerXml = null;
			return _this97;
		}

		_createClass(Transform, [{
			key: 'GetOutput',
			value: function GetOutput() {
				throw new XmlError(XE.METHOD_NOT_IMPLEMENTED);
			}
		}, {
			key: 'LoadInnerXml',
			value: function LoadInnerXml(node) {
				if (!node) {
					throw new XmlError(XE.PARAM_REQUIRED, "node");
				}
				this.innerXml = node;
			}
		}, {
			key: 'GetInnerXml',
			value: function GetInnerXml() {
				return this.innerXml;
			}
		}]);

		return Transform;
	}(XmlSignatureObject);
	__decorate([XmlAttribute({
		localName: XmlSignature.AttributeNames.Algorithm,
		defaultValue: ""
	})], Transform.prototype, "Algorithm", void 0);
	__decorate([XmlChildElement({
		localName: XmlSignature.ElementNames.XPath,
		defaultValue: ""
	})], Transform.prototype, "XPath", void 0);
	Transform = __decorate([XmlElement({
		localName: XmlSignature.ElementNames.Transform
	})], Transform);

	var XmlDsigBase64Transform = function (_Transform) {
		_inherits(XmlDsigBase64Transform, _Transform);

		function XmlDsigBase64Transform() {
			_classCallCheck(this, XmlDsigBase64Transform);

			var _this98 = _possibleConstructorReturn(this, (XmlDsigBase64Transform.__proto__ || Object.getPrototypeOf(XmlDsigBase64Transform)).apply(this, arguments));

			_this98.Algorithm = XmlSignature.AlgorithmNamespaces.XmlDsigBase64Transform;
			return _this98;
		}

		_createClass(XmlDsigBase64Transform, [{
			key: 'GetOutput',
			value: function GetOutput() {
				if (!this.innerXml) {
					throw new XmlError(XE.PARAM_REQUIRED, "innerXml");
				}
				return Convert.FromString(this.innerXml.textContent || "", "base64");
			}
		}]);

		return XmlDsigBase64Transform;
	}(Transform);

	var XmlDsigC14NTransform = function (_Transform2) {
		_inherits(XmlDsigC14NTransform, _Transform2);

		function XmlDsigC14NTransform() {
			_classCallCheck(this, XmlDsigC14NTransform);

			var _this99 = _possibleConstructorReturn(this, (XmlDsigC14NTransform.__proto__ || Object.getPrototypeOf(XmlDsigC14NTransform)).apply(this, arguments));

			_this99.Algorithm = "http://www.w3.org/TR/2001/REC-xml-c14n-20010315";
			_this99.xmlCanonicalizer = new XmlCanonicalizer(false, false);
			return _this99;
		}

		_createClass(XmlDsigC14NTransform, [{
			key: 'GetOutput',
			value: function GetOutput() {
				if (!this.innerXml) {
					throw new XmlError(XE.PARAM_REQUIRED, "innerXml");
				}
				return this.xmlCanonicalizer.Canonicalize(this.innerXml);
			}
		}]);

		return XmlDsigC14NTransform;
	}(Transform);

	var XmlDsigC14NWithCommentsTransform = function (_XmlDsigC14NTransform) {
		_inherits(XmlDsigC14NWithCommentsTransform, _XmlDsigC14NTransform);

		function XmlDsigC14NWithCommentsTransform() {
			_classCallCheck(this, XmlDsigC14NWithCommentsTransform);

			var _this100 = _possibleConstructorReturn(this, (XmlDsigC14NWithCommentsTransform.__proto__ || Object.getPrototypeOf(XmlDsigC14NWithCommentsTransform)).apply(this, arguments));

			_this100.Algorithm = "http://www.w3.org/TR/2001/REC-xml-c14n-20010315#WithComments";
			_this100.xmlCanonicalizer = new XmlCanonicalizer(true, false);
			return _this100;
		}

		return XmlDsigC14NWithCommentsTransform;
	}(XmlDsigC14NTransform);

	var XmlDsigEnvelopedSignatureTransform = function (_Transform3) {
		_inherits(XmlDsigEnvelopedSignatureTransform, _Transform3);

		function XmlDsigEnvelopedSignatureTransform() {
			_classCallCheck(this, XmlDsigEnvelopedSignatureTransform);

			var _this101 = _possibleConstructorReturn(this, (XmlDsigEnvelopedSignatureTransform.__proto__ || Object.getPrototypeOf(XmlDsigEnvelopedSignatureTransform)).apply(this, arguments));

			_this101.Algorithm = "http://www.w3.org/2000/09/xmldsig#enveloped-signature";
			return _this101;
		}

		_createClass(XmlDsigEnvelopedSignatureTransform, [{
			key: 'GetOutput',
			value: function GetOutput() {
				if (!this.innerXml) {
					throw new XmlError(XE.PARAM_REQUIRED, "innerXml");
				}
				var signature = Select(this.innerXml, ".//*[local-name(.)='Signature' and namespace-uri(.)='http://www.w3.org/2000/09/xmldsig#']")[0];
				if (signature) {
					signature.parentNode.removeChild(signature);
				}
				return this.innerXml;
			}
		}]);

		return XmlDsigEnvelopedSignatureTransform;
	}(Transform);

	var XmlDsigExcC14NTransform = function (_Transform4) {
		_inherits(XmlDsigExcC14NTransform, _Transform4);

		function XmlDsigExcC14NTransform() {
			_classCallCheck(this, XmlDsigExcC14NTransform);

			var _this102 = _possibleConstructorReturn(this, (XmlDsigExcC14NTransform.__proto__ || Object.getPrototypeOf(XmlDsigExcC14NTransform)).apply(this, arguments));

			_this102.Algorithm = "http://www.w3.org/2001/10/xml-exc-c14n#";
			_this102.xmlCanonicalizer = new XmlCanonicalizer(false, true);
			return _this102;
		}

		_createClass(XmlDsigExcC14NTransform, [{
			key: 'GetOutput',
			value: function GetOutput() {
				if (!this.innerXml) {
					throw new XmlError(XE.PARAM_REQUIRED, "innerXml");
				}
				return this.xmlCanonicalizer.Canonicalize(this.innerXml);
			}
		}, {
			key: 'InclusiveNamespacesPrefixList',
			get: function get() {
				return this.xmlCanonicalizer.InclusiveNamespacesPrefixList;
			},
			set: function set(value) {
				this.xmlCanonicalizer.InclusiveNamespacesPrefixList = value;
			}
		}]);

		return XmlDsigExcC14NTransform;
	}(Transform);

	var XmlDsigExcC14NWithCommentsTransform = function (_XmlDsigExcC14NTransf) {
		_inherits(XmlDsigExcC14NWithCommentsTransform, _XmlDsigExcC14NTransf);

		function XmlDsigExcC14NWithCommentsTransform() {
			_classCallCheck(this, XmlDsigExcC14NWithCommentsTransform);

			var _this103 = _possibleConstructorReturn(this, (XmlDsigExcC14NWithCommentsTransform.__proto__ || Object.getPrototypeOf(XmlDsigExcC14NWithCommentsTransform)).apply(this, arguments));

			_this103.Algorithm = "http://www.w3.org/2001/10/xml-exc-c14n#WithComments";
			_this103.xmlCanonicalizer = new XmlCanonicalizer(true, true);
			return _this103;
		}

		return XmlDsigExcC14NWithCommentsTransform;
	}(XmlDsigExcC14NTransform);

	var Transforms = function (_XmlSignatureCollecti3) {
		_inherits(Transforms, _XmlSignatureCollecti3);

		function Transforms() {
			_classCallCheck(this, Transforms);

			return _possibleConstructorReturn(this, (Transforms.__proto__ || Object.getPrototypeOf(Transforms)).apply(this, arguments));
		}

		_createClass(Transforms, [{
			key: 'OnLoadXml',
			value: function OnLoadXml(element) {
				_get(Transforms.prototype.__proto__ || Object.getPrototypeOf(Transforms.prototype), 'OnLoadXml', this).call(this, element);

				this.items = this.GetIterator().map(function (item) {
					switch (item.Algorithm) {
						case XmlSignature.AlgorithmNamespaces.XmlDsigEnvelopedSignatureTransform:
							return ChangeTransform(item, XmlDsigEnvelopedSignatureTransform);
						case XmlSignature.AlgorithmNamespaces.XmlDsigC14NTransform:
							return ChangeTransform(item, XmlDsigC14NTransform);
						case XmlSignature.AlgorithmNamespaces.XmlDsigC14NWithCommentsTransform:
							return ChangeTransform(item, XmlDsigC14NWithCommentsTransform);
						case XmlSignature.AlgorithmNamespaces.XmlDsigExcC14NTransform:
							return ChangeTransform(item, XmlDsigExcC14NTransform);
						case XmlSignature.AlgorithmNamespaces.XmlDsigExcC14NWithCommentsTransform:
							return ChangeTransform(item, XmlDsigExcC14NWithCommentsTransform);
						case XmlSignature.AlgorithmNamespaces.XmlDsigBase64Transform:
							return ChangeTransform(item, XmlDsigBase64Transform);
						default:
							throw new XmlError(XE.CRYPTOGRAPHIC_UNKNOWN_TRANSFORM, item.Algorithm);
					}
				});
			}
		}]);

		return Transforms;
	}(XmlSignatureCollection);
	Transforms = __decorate([XmlElement({
		localName: XmlSignature.ElementNames.Transforms,
		parser: Transform
	})], Transforms);
	function ChangeTransform(t1, t2) {
		var t = new t2();
		t.element = t1.Element;
		return t;
	}

	var Reference = function (_XmlSignatureObject6) {
		_inherits(Reference, _XmlSignatureObject6);

		function Reference(uri) {
			_classCallCheck(this, Reference);

			var _this105 = _possibleConstructorReturn(this, (Reference.__proto__ || Object.getPrototypeOf(Reference)).call(this));

			if (uri) {
				_this105.Uri = uri;
			}
			return _this105;
		}

		return Reference;
	}(XmlSignatureObject);
	__decorate([XmlAttribute({
		defaultValue: ""
	})], Reference.prototype, "Id", void 0);
	__decorate([XmlAttribute({
		localName: XmlSignature.AttributeNames.URI,
		defaultValue: ""
	})], Reference.prototype, "Uri", void 0);
	__decorate([XmlAttribute({
		localName: XmlSignature.AttributeNames.Type,
		defaultValue: ""
	})], Reference.prototype, "Type", void 0);
	__decorate([XmlChildElement({
		parser: Transforms
	})], Reference.prototype, "Transforms", void 0);
	__decorate([XmlChildElement({
		required: true,
		parser: DigestMethod
	})], Reference.prototype, "DigestMethod", void 0);
	__decorate([XmlChildElement({
		required: true,
		localName: XmlSignature.ElementNames.DigestValue,
		namespaceURI: XmlSignature.NamespaceURI,
		prefix: XmlSignature.DefaultPrefix,
		converter: XmlBase64Converter
	})], Reference.prototype, "DigestValue", void 0);
	Reference = __decorate([XmlElement({
		localName: XmlSignature.ElementNames.Reference
	})], Reference);
	var References = function (_XmlSignatureCollecti4) {
		_inherits(References, _XmlSignatureCollecti4);

		function References() {
			_classCallCheck(this, References);

			return _possibleConstructorReturn(this, (References.__proto__ || Object.getPrototypeOf(References)).apply(this, arguments));
		}

		return References;
	}(XmlSignatureCollection);
	References = __decorate([XmlElement({
		localName: "References",
		parser: Reference
	})], References);

	var SignatureMethodOther = function (_XmlSignatureCollecti5) {
		_inherits(SignatureMethodOther, _XmlSignatureCollecti5);

		function SignatureMethodOther() {
			_classCallCheck(this, SignatureMethodOther);

			return _possibleConstructorReturn(this, (SignatureMethodOther.__proto__ || Object.getPrototypeOf(SignatureMethodOther)).apply(this, arguments));
		}

		_createClass(SignatureMethodOther, [{
			key: 'OnLoadXml',
			value: function OnLoadXml(element) {
				for (var i = 0; i < element.childNodes.length; i++) {
					var _node = element.childNodes.item(i);
					if (_node.nodeType !== XmlNodeType.Element || _node.nodeName === XmlSignature.ElementNames.HMACOutputLength) {
						continue;
					}
					var ParserClass = void 0;
					switch (_node.localName) {
						case XmlSignature.ElementNames.RSAPSSParams:
							ParserClass = PssAlgorithmParams;
							break;
						default:
							break;
					}
					if (ParserClass) {
						var _xml = new ParserClass();
						_xml.LoadXml(_node);
						this.Add(_xml);
					}
				}
			}
		}]);

		return SignatureMethodOther;
	}(XmlSignatureCollection);
	SignatureMethodOther = __decorate([XmlElement({
		localName: "Other"
	})], SignatureMethodOther);
	var SignatureMethod = function (_XmlSignatureObject7) {
		_inherits(SignatureMethod, _XmlSignatureObject7);

		function SignatureMethod() {
			_classCallCheck(this, SignatureMethod);

			return _possibleConstructorReturn(this, (SignatureMethod.__proto__ || Object.getPrototypeOf(SignatureMethod)).apply(this, arguments));
		}

		return SignatureMethod;
	}(XmlSignatureObject);
	__decorate([XmlAttribute({
		localName: XmlSignature.AttributeNames.Algorithm,
		required: true,
		defaultValue: ""
	})], SignatureMethod.prototype, "Algorithm", void 0);
	__decorate([XmlChildElement({
		localName: XmlSignature.ElementNames.HMACOutputLength,
		namespaceURI: XmlSignature.NamespaceURI,
		prefix: XmlSignature.DefaultPrefix,
		converter: XmlNumberConverter
	})], SignatureMethod.prototype, "HMACOutputLength", void 0);
	__decorate([XmlChildElement({
		parser: SignatureMethodOther,
		noRoot: true,
		minOccurs: 0
	})], SignatureMethod.prototype, "Any", void 0);
	SignatureMethod = __decorate([XmlElement({
		localName: XmlSignature.ElementNames.SignatureMethod
	})], SignatureMethod);

	var SignedInfo = function (_XmlSignatureObject8) {
		_inherits(SignedInfo, _XmlSignatureObject8);

		function SignedInfo() {
			_classCallCheck(this, SignedInfo);

			return _possibleConstructorReturn(this, (SignedInfo.__proto__ || Object.getPrototypeOf(SignedInfo)).apply(this, arguments));
		}

		return SignedInfo;
	}(XmlSignatureObject);
	__decorate([XmlAttribute({
		localName: XmlSignature.AttributeNames.Id,
		defaultValue: ""
	})], SignedInfo.prototype, "Id", void 0);
	__decorate([XmlChildElement({
		parser: CanonicalizationMethod,
		required: true
	})], SignedInfo.prototype, "CanonicalizationMethod", void 0);
	__decorate([XmlChildElement({
		parser: SignatureMethod,
		required: true
	})], SignedInfo.prototype, "SignatureMethod", void 0);
	__decorate([XmlChildElement({
		parser: References,
		minOccurs: 1,
		noRoot: true
	})], SignedInfo.prototype, "References", void 0);
	SignedInfo = __decorate([XmlElement({
		localName: XmlSignature.ElementNames.SignedInfo
	})], SignedInfo);

	var Signature$$1 = function (_XmlSignatureObject9) {
		_inherits(Signature$$1, _XmlSignatureObject9);

		function Signature$$1() {
			_classCallCheck(this, Signature$$1);

			return _possibleConstructorReturn(this, (Signature$$1.__proto__ || Object.getPrototypeOf(Signature$$1)).apply(this, arguments));
		}

		return Signature$$1;
	}(XmlSignatureObject);
	__decorate([XmlAttribute({
		localName: XmlSignature.AttributeNames.Id,
		defaultValue: ""
	})], Signature$$1.prototype, "Id", void 0);
	__decorate([XmlChildElement({
		parser: SignedInfo,
		required: true
	})], Signature$$1.prototype, "SignedInfo", void 0);
	__decorate([XmlChildElement({
		localName: XmlSignature.ElementNames.SignatureValue,
		namespaceURI: XmlSignature.NamespaceURI,
		prefix: XmlSignature.DefaultPrefix,
		required: true,
		converter: XmlBase64Converter,
		defaultValue: null
	})], Signature$$1.prototype, "SignatureValue", void 0);
	__decorate([XmlChildElement({
		parser: KeyInfo
	})], Signature$$1.prototype, "KeyInfo", void 0);
	__decorate([XmlChildElement({
		parser: DataObjects,
		noRoot: true
	})], Signature$$1.prototype, "ObjectList", void 0);
	Signature$$1 = __decorate([XmlElement({
		localName: XmlSignature.ElementNames.Signature
	})], Signature$$1);

	var NAMESPACE_URI = "http://www.w3.org/2001/04/xmldsig-more#";
	var PREFIX = "ecdsa";
	var EcdsaPublicKey = function (_XmlObject3) {
		_inherits(EcdsaPublicKey, _XmlObject3);

		function EcdsaPublicKey() {
			_classCallCheck(this, EcdsaPublicKey);

			return _possibleConstructorReturn(this, (EcdsaPublicKey.__proto__ || Object.getPrototypeOf(EcdsaPublicKey)).apply(this, arguments));
		}

		return EcdsaPublicKey;
	}(XmlObject);
	__decorate([XmlChildElement({
		localName: XmlSignature.ElementNames.X,
		namespaceURI: NAMESPACE_URI,
		prefix: PREFIX,
		required: true,
		converter: XmlBase64Converter
	})], EcdsaPublicKey.prototype, "X", void 0);
	__decorate([XmlChildElement({
		localName: XmlSignature.ElementNames.Y,
		namespaceURI: NAMESPACE_URI,
		prefix: PREFIX,
		required: true,
		converter: XmlBase64Converter
	})], EcdsaPublicKey.prototype, "Y", void 0);
	EcdsaPublicKey = __decorate([XmlElement({
		localName: XmlSignature.ElementNames.PublicKey,
		namespaceURI: NAMESPACE_URI,
		prefix: PREFIX
	})], EcdsaPublicKey);
	var NamedCurve = function (_XmlObject4) {
		_inherits(NamedCurve, _XmlObject4);

		function NamedCurve() {
			_classCallCheck(this, NamedCurve);

			return _possibleConstructorReturn(this, (NamedCurve.__proto__ || Object.getPrototypeOf(NamedCurve)).apply(this, arguments));
		}

		return NamedCurve;
	}(XmlObject);
	__decorate([XmlAttribute({
		localName: XmlSignature.AttributeNames.URI,
		required: true
	})], NamedCurve.prototype, "Uri", void 0);
	NamedCurve = __decorate([XmlElement({
		localName: XmlSignature.ElementNames.NamedCurve,
		namespaceURI: NAMESPACE_URI,
		prefix: PREFIX
	})], NamedCurve);
	var DomainParameters = function (_XmlObject5) {
		_inherits(DomainParameters, _XmlObject5);

		function DomainParameters() {
			_classCallCheck(this, DomainParameters);

			return _possibleConstructorReturn(this, (DomainParameters.__proto__ || Object.getPrototypeOf(DomainParameters)).apply(this, arguments));
		}

		return DomainParameters;
	}(XmlObject);
	__decorate([XmlChildElement({
		parser: NamedCurve
	})], DomainParameters.prototype, "NamedCurve", void 0);
	DomainParameters = __decorate([XmlElement({
		localName: XmlSignature.ElementNames.DomainParameters,
		namespaceURI: NAMESPACE_URI,
		prefix: PREFIX
	})], DomainParameters);

	var EcdsaKeyValue = function (_KeyInfoClause) {
		_inherits(EcdsaKeyValue, _KeyInfoClause);

		function EcdsaKeyValue() {
			_classCallCheck(this, EcdsaKeyValue);

			var _this114 = _possibleConstructorReturn(this, (EcdsaKeyValue.__proto__ || Object.getPrototypeOf(EcdsaKeyValue)).apply(this, arguments));

			_this114.name = XmlSignature.ElementNames.ECDSAKeyValue;
			_this114.key = null;
			_this114.jwk = null;
			_this114.keyUsage = null;
			return _this114;
		}

		_createClass(EcdsaKeyValue, [{
			key: 'importKey',
			value: function importKey(key) {
				var _this115 = this;

				return new Promise(function (resolve, reject) {
					if (key.algorithm.name.toUpperCase() !== "ECDSA") {
						throw new XmlError(XE.ALGORITHM_WRONG_NAME, key.algorithm.name);
					}
					_this115.key = key;
					Application.crypto.subtle.exportKey("jwk", key).then(function (jwk) {
						_this115.jwk = jwk;
						_this115.PublicKey = new EcdsaPublicKey();
						_this115.PublicKey.X = Convert.FromString(jwk.x, "base64url");
						_this115.PublicKey.Y = Convert.FromString(jwk.y, "base64url");
						if (!_this115.DomainParameters) {
							_this115.DomainParameters = new DomainParameters();
						}
						if (!_this115.DomainParameters.NamedCurve) {
							_this115.DomainParameters.NamedCurve = new NamedCurve();
						}
						_this115.DomainParameters.NamedCurve.Uri = GetNamedCurveOid(jwk.crv);
						_this115.keyUsage = key.usages;
						return Promise.resolve(_this115);
					}).then(resolve, reject);
				});
			}
		}, {
			key: 'exportKey',
			value: function exportKey(alg) {
				var _this116 = this;

				return Promise.resolve().then(function () {
					if (_this116.key) {
						return _this116.key;
					}

					var x = Convert.ToBase64Url(_this116.PublicKey.X);
					var y = Convert.ToBase64Url(_this116.PublicKey.Y);
					var crv = GetNamedCurveFromOid(_this116.DomainParameters.NamedCurve.Uri);
					var jwk = {
						kty: "EC",
						crv: crv,
						x: x,
						y: y,
						ext: true
					};
					_this116.keyUsage = ["verify"];
					return Application.crypto.subtle.importKey("jwk", jwk, { name: "ECDSA", namedCurve: crv }, true, _this116.keyUsage);
				}).then(function (key) {
					_this116.key = key;
					return _this116.key;
				});
			}
		}, {
			key: 'NamedCurve',
			get: function get() {
				return GetNamedCurveOid(this.DomainParameters.NamedCurve.Uri);
			}
		}]);

		return EcdsaKeyValue;
	}(KeyInfoClause);
	__decorate([XmlChildElement({
		parser: DomainParameters
	})], EcdsaKeyValue.prototype, "DomainParameters", void 0);
	__decorate([XmlChildElement({
		parser: EcdsaPublicKey,
		required: true
	})], EcdsaKeyValue.prototype, "PublicKey", void 0);
	EcdsaKeyValue = __decorate([XmlElement({
		localName: XmlSignature.ElementNames.ECDSAKeyValue,
		namespaceURI: NAMESPACE_URI,
		prefix: PREFIX
	})], EcdsaKeyValue);
	function GetNamedCurveOid(namedCurve) {
		switch (namedCurve) {
			case "P-256":
				return "urn:oid:1.2.840.10045.3.1.7";
			case "P-384":
				return "urn:oid:1.3.132.0.34";
			case "P-521":
				return "urn:oid:1.3.132.0.35";
		}
		throw new XmlError(XE.CRYPTOGRAPHIC, "Unknown NamedCurve");
	}
	function GetNamedCurveFromOid(oid) {
		switch (oid) {
			case "urn:oid:1.2.840.10045.3.1.7":
				return "P-256";
			case "urn:oid:1.3.132.0.34":
				return "P-384";
			case "urn:oid:1.3.132.0.35":
				return "P-521";
		}
		throw new XmlError(XE.CRYPTOGRAPHIC, "Unknown NamedCurve OID");
	}

	var RsaKeyValue = function (_KeyInfoClause2) {
		_inherits(RsaKeyValue, _KeyInfoClause2);

		function RsaKeyValue() {
			_classCallCheck(this, RsaKeyValue);

			var _this117 = _possibleConstructorReturn(this, (RsaKeyValue.__proto__ || Object.getPrototypeOf(RsaKeyValue)).apply(this, arguments));

			_this117.key = null;
			_this117.jwk = null;
			_this117.keyUsage = [];
			return _this117;
		}

		_createClass(RsaKeyValue, [{
			key: 'importKey',
			value: function importKey(key) {
				var _this118 = this;

				return new Promise(function (resolve, reject) {
					var algName = key.algorithm.name.toUpperCase();
					if (algName !== RSA_PKCS1.toUpperCase() && algName !== RSA_PSS.toUpperCase()) {
						throw new XmlError(XE.ALGORITHM_WRONG_NAME, key.algorithm.name);
					}
					_this118.key = key;
					Application.crypto.subtle.exportKey("jwk", key).then(function (jwk) {
						_this118.jwk = jwk;
						_this118.Modulus = Convert.FromBase64Url(jwk.n);
						_this118.Exponent = Convert.FromBase64Url(jwk.e);
						_this118.keyUsage = key.usages;
						return Promise.resolve(_this118);
					}).then(resolve, reject);
				});
			}
		}, {
			key: 'exportKey',
			value: function exportKey(alg) {
				var _this119 = this;

				return new Promise(function (resolve, reject) {
					if (_this119.key) {
						return resolve(_this119.key);
					}

					if (!_this119.Modulus) {
						throw new XmlError(XE.CRYPTOGRAPHIC, "RsaKeyValue has no Modulus");
					}
					var modulus = Convert.ToBase64Url(_this119.Modulus);
					if (!_this119.Exponent) {
						throw new XmlError(XE.CRYPTOGRAPHIC, "RsaKeyValue has no Exponent");
					}
					var exponent = Convert.ToBase64Url(_this119.Exponent);
					var algJwk = void 0;
					switch (alg.name.toUpperCase()) {
						case RSA_PKCS1.toUpperCase():
							algJwk = "R";
							break;
						case RSA_PSS.toUpperCase():
							algJwk = "P";
							break;
						default:
							throw new XmlError(XE.ALGORITHM_NOT_SUPPORTED, alg.name);
					}

					switch (alg.hash.name.toUpperCase()) {
						case SHA1:
							algJwk += "S1";
							break;
						case SHA256:
							algJwk += "S256";
							break;
						case SHA384:
							algJwk += "S384";
							break;
						case SHA512:
							algJwk += "S512";
							break;
					}
					var jwk = {
						kty: "RSA",
						alg: algJwk,
						n: modulus,
						e: exponent,
						ext: true
					};
					Application.crypto.subtle.importKey("jwk", jwk, alg, true, _this119.keyUsage).then(resolve, reject);
				});
			}
		}, {
			key: 'LoadXml',
			value: function LoadXml(node) {
				_get(RsaKeyValue.prototype.__proto__ || Object.getPrototypeOf(RsaKeyValue.prototype), 'LoadXml', this).call(this, node);
				this.keyUsage = ["verify"];
			}
		}]);

		return RsaKeyValue;
	}(KeyInfoClause);
	__decorate([XmlChildElement({
		localName: XmlSignature.ElementNames.Modulus,
		prefix: XmlSignature.DefaultPrefix,
		namespaceURI: XmlSignature.NamespaceURI,
		required: true,
		converter: XmlBase64Converter
	})], RsaKeyValue.prototype, "Modulus", void 0);
	__decorate([XmlChildElement({
		localName: XmlSignature.ElementNames.Exponent,
		prefix: XmlSignature.DefaultPrefix,
		namespaceURI: XmlSignature.NamespaceURI,
		required: true,
		converter: XmlBase64Converter
	})], RsaKeyValue.prototype, "Exponent", void 0);
	RsaKeyValue = __decorate([XmlElement({
		localName: XmlSignature.ElementNames.RSAKeyValue
	})], RsaKeyValue);

	var NAMESPACE_URI$1 = "http://www.w3.org/2007/05/xmldsig-more#";
	var PREFIX$1 = "pss";
	var MaskGenerationFunction = function (_XmlObject6) {
		_inherits(MaskGenerationFunction, _XmlObject6);

		function MaskGenerationFunction() {
			_classCallCheck(this, MaskGenerationFunction);

			return _possibleConstructorReturn(this, (MaskGenerationFunction.__proto__ || Object.getPrototypeOf(MaskGenerationFunction)).apply(this, arguments));
		}

		return MaskGenerationFunction;
	}(XmlObject);
	__decorate([XmlChildElement({
		parser: DigestMethod
	})], MaskGenerationFunction.prototype, "DigestMethod", void 0);
	__decorate([XmlAttribute({
		localName: XmlSignature.AttributeNames.Algorithm,
		defaultValue: "http://www.w3.org/2007/05/xmldsig-more#MGF1"
	})], MaskGenerationFunction.prototype, "Algorithm", void 0);
	MaskGenerationFunction = __decorate([XmlElement({
		localName: XmlSignature.ElementNames.MaskGenerationFunction,
		prefix: PREFIX$1,
		namespaceURI: NAMESPACE_URI$1
	})], MaskGenerationFunction);
	var PssAlgorithmParams = PssAlgorithmParams_1 = function (_XmlObject7) {
		_inherits(PssAlgorithmParams, _XmlObject7);

		function PssAlgorithmParams(algorithm) {
			_classCallCheck(this, PssAlgorithmParams);

			var _this121 = _possibleConstructorReturn(this, (PssAlgorithmParams.__proto__ || Object.getPrototypeOf(PssAlgorithmParams)).call(this));

			if (algorithm) {
				_this121.FromAlgorithm(algorithm);
			}
			return _this121;
		}

		_createClass(PssAlgorithmParams, [{
			key: 'FromAlgorithm',
			value: function FromAlgorithm(algorithm) {
				this.DigestMethod = new DigestMethod();
				var digest = CryptoConfig.GetHashAlgorithm(algorithm.hash);
				this.DigestMethod.Algorithm = digest.namespaceURI;
				if (algorithm.saltLength) {
					this.SaltLength = algorithm.saltLength;
				}
			}
		}], [{
			key: 'FromAlgorithm',
			value: function FromAlgorithm(algorithm) {
				return new PssAlgorithmParams_1(algorithm);
			}
		}]);

		return PssAlgorithmParams;
	}(XmlObject);
	__decorate([XmlChildElement({
		parser: DigestMethod
	})], PssAlgorithmParams.prototype, "DigestMethod", void 0);
	__decorate([XmlChildElement({
		parser: MaskGenerationFunction
	})], PssAlgorithmParams.prototype, "MGF", void 0);
	__decorate([XmlChildElement({
		converter: XmlNumberConverter,
		prefix: PREFIX$1,
		namespaceURI: NAMESPACE_URI$1
	})], PssAlgorithmParams.prototype, "SaltLength", void 0);
	__decorate([XmlChildElement({
		converter: XmlNumberConverter
	})], PssAlgorithmParams.prototype, "TrailerField", void 0);
	PssAlgorithmParams = PssAlgorithmParams_1 = __decorate([XmlElement({
		localName: XmlSignature.ElementNames.RSAPSSParams,
		prefix: PREFIX$1,
		namespaceURI: NAMESPACE_URI$1
	})], PssAlgorithmParams);
	var PssAlgorithmParams_1;

	var KeyValue = function (_KeyInfoClause3) {
		_inherits(KeyValue, _KeyInfoClause3);

		function KeyValue(value) {
			_classCallCheck(this, KeyValue);

			var _this122 = _possibleConstructorReturn(this, (KeyValue.__proto__ || Object.getPrototypeOf(KeyValue)).call(this));

			if (value) {
				_this122.Value = value;
			}
			return _this122;
		}

		_createClass(KeyValue, [{
			key: 'importKey',
			value: function importKey(key) {
				var _this123 = this;

				return Promise.resolve().then(function () {
					switch (key.algorithm.name.toUpperCase()) {
						case RSA_PSS.toUpperCase():
						case RSA_PKCS1.toUpperCase():
							_this123.Value = new RsaKeyValue();
							return _this123.Value.importKey(key);
						case ECDSA.toUpperCase():
							_this123.Value = new EcdsaKeyValue();
							return _this123.Value.importKey(key);
						default:
							throw new XmlError(XE.ALGORITHM_NOT_SUPPORTED, key.algorithm.name);
					}
				}).then(function () {
					return _this123;
				});
			}
		}, {
			key: 'exportKey',
			value: function exportKey(alg) {
				var _this124 = this;

				return Promise.resolve().then(function () {
					if (!_this124.Value) {
						throw new XmlError(XE.NULL_REFERENCE);
					}
					return _this124.Value.exportKey(alg);
				});
			}
		}, {
			key: 'OnGetXml',
			value: function OnGetXml(element) {
				if (!this.Value) {
					throw new XmlError(XE.CRYPTOGRAPHIC, "KeyValue has empty value");
				}
				var node = this.Value.GetXml();
				if (node) {
					element.appendChild(node);
				}
			}
		}, {
			key: 'Value',
			set: function set(v) {
				this.element = null;
				this.value = v;
			},
			get: function get() {
				return this.value;
			}
		}]);

		return KeyValue;
	}(KeyInfoClause);
	KeyValue = __decorate([XmlElement({
		localName: XmlSignature.ElementNames.KeyValue
	})], KeyValue);

	var OID = {
		"2.5.4.3": {
			short: "CN",
			long: "CommonName"
		},
		"2.5.4.6": {
			short: "C",
			long: "Country"
		},
		"2.5.4.5": {
			long: "DeviceSerialNumber"
		},
		"0.9.2342.19200300.100.1.25": {
			short: "DC",
			long: "DomainComponent"
		},
		"1.2.840.113549.1.9.1": {
			short: "E",
			long: "EMail"
		},
		"2.5.4.42": {
			short: "G",
			long: "GivenName"
		},
		"2.5.4.43": {
			short: "I",
			long: "Initials"
		},
		"2.5.4.7": {
			short: "L",
			long: "Locality"
		},
		"2.5.4.10": {
			short: "O",
			long: "Organization"
		},
		"2.5.4.11": {
			short: "OU",
			long: "OrganizationUnit"
		},
		"2.5.4.8": {
			short: "ST",
			long: "State"
		},
		"2.5.4.9": {
			short: "Street",
			long: "StreetAddress"
		},
		"2.5.4.4": {
			short: "SN",
			long: "SurName"
		},
		"2.5.4.12": {
			short: "T",
			long: "Title"
		},
		"1.2.840.113549.1.9.8": {
			long: "UnstructuredAddress"
		},
		"1.2.840.113549.1.9.2": {
			long: "UnstructuredName"
		}
	};

	var X509Certificate = function () {
		function X509Certificate(rawData) {
			_classCallCheck(this, X509Certificate);

			this.publicKey = null;
			if (rawData) {
				var buf = new Uint8Array(rawData);
				this.LoadRaw(buf);
				this.raw = buf;
			}
		}

		_createClass(X509Certificate, [{
			key: 'Thumbprint',
			value: function Thumbprint() {
				var algName = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : "SHA-1";

				return Application.crypto.subtle.digest(algName, this.raw);
			}
		}, {
			key: 'GetRaw',
			value: function GetRaw() {
				return this.raw;
			}
		}, {
			key: 'exportKey',
			value: function exportKey(algorithm) {
				var _this125 = this;

				return Promise.resolve().then(function () {
					if (!getCrypto()) {
						setEngine(Application.crypto.name, new CryptoEngine({ name: Application.crypto.name, crypto: Application.crypto }), Application.crypto.subtle);
					}
					var alg = {
						algorithm: algorithm,
						usages: ["verify"]
					};
					if (alg.algorithm.name.toUpperCase() === ECDSA) {
						alg.algorithm.namedCurve = _this125.simpl.subjectPublicKeyInfo.toJSON().crv;
					}
					return _this125.simpl.getPublicKey({ algorithm: alg }).then(function (key) {
						_this125.publicKey = key;
						return key;
					});
				});
			}
		}, {
			key: 'NameToString',
			value: function NameToString(name) {
				var splitter = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : ",";

				var res = [];
				name.typesAndValues.forEach(function (typeAndValue) {
					var type = typeAndValue.type;
					var oid = OID[type.toString()];
					var name2 = oid ? oid.short : null;
					res.push((name2 ? name2 : type) + '=' + typeAndValue.value.valueBlock.value);
				});
				return res.join(splitter + " ");
			}
		}, {
			key: 'LoadRaw',
			value: function LoadRaw(rawData) {
				this.raw = new Uint8Array(rawData);
				var asn1 = fromBER(this.raw.buffer);
				this.simpl = new Certificate({ schema: asn1.result });
			}
		}, {
			key: 'SerialNumber',
			get: function get() {
				return this.simpl.serialNumber.valueBlock.toString();
			}
		}, {
			key: 'Issuer',
			get: function get() {
				return this.NameToString(this.simpl.issuer);
			}
		}, {
			key: 'Subject',
			get: function get() {
				return this.NameToString(this.simpl.subject);
			}
		}, {
			key: 'PublicKey',
			get: function get() {
				return this.publicKey;
			}
		}]);

		return X509Certificate;
	}();

	var X509IssuerSerial = function (_XmlSignatureObject10) {
		_inherits(X509IssuerSerial, _XmlSignatureObject10);

		function X509IssuerSerial() {
			_classCallCheck(this, X509IssuerSerial);

			return _possibleConstructorReturn(this, (X509IssuerSerial.__proto__ || Object.getPrototypeOf(X509IssuerSerial)).apply(this, arguments));
		}

		return X509IssuerSerial;
	}(XmlSignatureObject);
	__decorate([XmlChildElement({
		localName: XmlSignature.ElementNames.X509IssuerName,
		namespaceURI: XmlSignature.NamespaceURI,
		prefix: XmlSignature.DefaultPrefix,
		required: true
	})], X509IssuerSerial.prototype, "X509IssuerName", void 0);
	__decorate([XmlChildElement({
		localName: XmlSignature.ElementNames.X509SerialNumber,
		namespaceURI: XmlSignature.NamespaceURI,
		prefix: XmlSignature.DefaultPrefix,
		required: true
	})], X509IssuerSerial.prototype, "X509SerialNumber", void 0);
	X509IssuerSerial = __decorate([XmlElement({ localName: XmlSignature.ElementNames.X509IssuerSerial })], X509IssuerSerial);
	var X509IncludeOption;
	(function (X509IncludeOption) {
		X509IncludeOption[X509IncludeOption["None"] = 0] = "None";
		X509IncludeOption[X509IncludeOption["EndCertOnly"] = 1] = "EndCertOnly";
		X509IncludeOption[X509IncludeOption["ExcludeRoot"] = 2] = "ExcludeRoot";
		X509IncludeOption[X509IncludeOption["WholeChain"] = 3] = "WholeChain";
	})(X509IncludeOption || (X509IncludeOption = {}));

	var KeyInfoX509Data = function (_KeyInfoClause4) {
		_inherits(KeyInfoX509Data, _KeyInfoClause4);

		function KeyInfoX509Data(cert) {
			var includeOptions = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : X509IncludeOption.None;

			_classCallCheck(this, KeyInfoX509Data);

			var _this127 = _possibleConstructorReturn(this, (KeyInfoX509Data.__proto__ || Object.getPrototypeOf(KeyInfoX509Data)).call(this));

			_this127.x509crl = null;
			_this127.SubjectKeyIdList = [];
			_this127.key = null;
			if (cert) {
				if (cert instanceof Uint8Array) {
					_this127.AddCertificate(new X509Certificate(cert));
				} else if (cert instanceof X509Certificate) {
					switch (includeOptions) {
						case X509IncludeOption.None:
						case X509IncludeOption.EndCertOnly:
							_this127.AddCertificate(cert);
							break;
						case X509IncludeOption.ExcludeRoot:
							_this127.AddCertificatesChainFrom(cert, false);
							break;
						case X509IncludeOption.WholeChain:
							_this127.AddCertificatesChainFrom(cert, true);
							break;
					}
				}
			}
			return _this127;
		}

		_createClass(KeyInfoX509Data, [{
			key: 'importKey',
			value: function importKey(key) {
				return Promise.reject(new XmlError(XE.METHOD_NOT_SUPPORTED));
			}
		}, {
			key: 'exportKey',
			value: function exportKey(alg) {
				var _this128 = this;

				return Promise.resolve().then(function () {
					if (_this128.Certificates.length) {
						return _this128.Certificates[0].exportKey(alg);
					}
					throw new XmlError(XE.NULL_REFERENCE);
				}).then(function (key) {
					_this128.key = key;
					return key;
				});
			}
		}, {
			key: 'AddCertificate',
			value: function AddCertificate(certificate) {
				if (!certificate) {
					throw new XmlError(XE.PARAM_REQUIRED, "certificate");
				}
				if (!this.X509CertificateList) {
					this.X509CertificateList = [];
				}
				this.X509CertificateList.push(certificate);
			}
		}, {
			key: 'AddIssuerSerial',
			value: function AddIssuerSerial(issuerName, serialNumber) {
				if (issuerName == null) {
					throw new XmlError(XE.PARAM_REQUIRED, "issuerName");
				}
				if (this.IssuerSerialList == null) {
					this.IssuerSerialList = [];
				}
				var xis = { issuerName: issuerName, serialNumber: serialNumber };
				this.IssuerSerialList.push(xis);
			}
		}, {
			key: 'AddSubjectKeyId',
			value: function AddSubjectKeyId(subjectKeyId) {
				if (this.SubjectKeyIdList) {
					this.SubjectKeyIdList = [];
				}
				if (typeof subjectKeyId === "string") {
					if (subjectKeyId != null) {
						var id = void 0;
						id = Convert.FromBase64(subjectKeyId);
						this.SubjectKeyIdList.push(id);
					}
				} else {
					this.SubjectKeyIdList.push(subjectKeyId);
				}
			}
		}, {
			key: 'AddSubjectName',
			value: function AddSubjectName(subjectName) {
				if (this.SubjectNameList == null) {
					this.SubjectNameList = [];
				}
				this.SubjectNameList.push(subjectName);
			}
		}, {
			key: 'GetXml',
			value: function GetXml() {
				var doc = this.CreateDocument();
				var xel = this.CreateElement(doc);
				var prefix = this.GetPrefix();

				if (this.IssuerSerialList != null && this.IssuerSerialList.length > 0) {
					this.IssuerSerialList.forEach(function (iser) {
						var isl = doc.createElementNS(XmlSignature.NamespaceURI, prefix + XmlSignature.ElementNames.X509IssuerSerial);
						var xin = doc.createElementNS(XmlSignature.NamespaceURI, prefix + XmlSignature.ElementNames.X509IssuerName);
						xin.textContent = iser.issuerName;
						isl.appendChild(xin);
						var xsn = doc.createElementNS(XmlSignature.NamespaceURI, prefix + XmlSignature.ElementNames.X509SerialNumber);
						xsn.textContent = iser.serialNumber;
						isl.appendChild(xsn);
						xel.appendChild(isl);
					});
				}

				if (this.SubjectKeyIdList != null && this.SubjectKeyIdList.length > 0) {
					this.SubjectKeyIdList.forEach(function (skid) {
						var ski = doc.createElementNS(XmlSignature.NamespaceURI, prefix + XmlSignature.ElementNames.X509SKI);
						ski.textContent = Convert.ToBase64(skid);
						xel.appendChild(ski);
					});
				}

				if (this.SubjectNameList != null && this.SubjectNameList.length > 0) {
					this.SubjectNameList.forEach(function (subject) {
						var sn = doc.createElementNS(XmlSignature.NamespaceURI, prefix + XmlSignature.ElementNames.X509SubjectName);
						sn.textContent = subject;
						xel.appendChild(sn);
					});
				}

				if (this.X509CertificateList != null && this.X509CertificateList.length > 0) {
					this.X509CertificateList.forEach(function (x509) {
						var cert = doc.createElementNS(XmlSignature.NamespaceURI, prefix + XmlSignature.ElementNames.X509Certificate);
						cert.textContent = Convert.ToBase64(x509.GetRaw());
						xel.appendChild(cert);
					});
				}

				if (this.x509crl != null) {
					var crl = doc.createElementNS(XmlSignature.NamespaceURI, prefix + XmlSignature.ElementNames.X509CRL);
					crl.textContent = Convert.ToBase64(this.x509crl);
					xel.appendChild(crl);
				}
				return xel;
			}
		}, {
			key: 'LoadXml',
			value: function LoadXml(element) {
				var _this129 = this;

				_get(KeyInfoX509Data.prototype.__proto__ || Object.getPrototypeOf(KeyInfoX509Data.prototype), 'LoadXml', this).call(this, element);
				if (this.IssuerSerialList) {
					this.IssuerSerialList = [];
				}
				if (this.SubjectKeyIdList) {
					this.SubjectKeyIdList = [];
				}
				if (this.SubjectNameList) {
					this.SubjectNameList = [];
				}
				if (this.X509CertificateList) {
					this.X509CertificateList = [];
				}
				this.x509crl = null;

				var xnl = this.GetChildren(XmlSignature.ElementNames.X509IssuerSerial);
				if (xnl) {
					xnl.forEach(function (xel) {
						var issuer = XmlSignatureObject.GetChild(xel, XmlSignature.ElementNames.X509IssuerName, XmlSignature.NamespaceURI, true);
						var serial = XmlSignatureObject.GetChild(xel, XmlSignature.ElementNames.X509SerialNumber, XmlSignature.NamespaceURI, true);
						if (issuer && issuer.textContent && serial && serial.textContent) {
							_this129.AddIssuerSerial(issuer.textContent, serial.textContent);
						}
					});
				}

				xnl = this.GetChildren(XmlSignature.ElementNames.X509SKI);
				if (xnl) {
					xnl.forEach(function (xel) {
						if (xel.textContent) {
							var skid = Convert.FromBase64(xel.textContent);
							_this129.AddSubjectKeyId(skid);
						}
					});
				}

				xnl = this.GetChildren(XmlSignature.ElementNames.X509SubjectName);
				if (xnl != null) {
					xnl.forEach(function (xel) {
						if (xel.textContent) {
							_this129.AddSubjectName(xel.textContent);
						}
					});
				}

				xnl = this.GetChildren(XmlSignature.ElementNames.X509Certificate);
				if (xnl) {
					xnl.forEach(function (xel) {
						if (xel.textContent) {
							var cert = Convert.FromBase64(xel.textContent);
							_this129.AddCertificate(new X509Certificate(cert));
						}
					});
				}

				var x509el = this.GetChild(XmlSignature.ElementNames.X509CRL, false);
				if (x509el && x509el.textContent) {
					this.x509crl = Convert.FromBase64(x509el.textContent);
				}
			}
		}, {
			key: 'AddCertificatesChainFrom',
			value: function AddCertificatesChainFrom(cert, root) {
				throw new XmlError(XE.METHOD_NOT_IMPLEMENTED);
			}
		}, {
			key: 'Key',
			get: function get() {
				return this.key;
			}
		}, {
			key: 'Certificates',
			get: function get() {
				return this.X509CertificateList;
			}
		}, {
			key: 'CRL',
			get: function get() {
				return this.x509crl;
			},
			set: function set(value) {
				this.x509crl = value;
			}
		}, {
			key: 'IssuerSerials',
			get: function get() {
				return this.IssuerSerialList;
			}
		}, {
			key: 'SubjectKeyIds',
			get: function get() {
				return this.SubjectKeyIdList;
			}
		}, {
			key: 'SubjectNames',
			get: function get() {
				return this.SubjectNameList;
			}
		}]);

		return KeyInfoX509Data;
	}(KeyInfoClause);
	KeyInfoX509Data = __decorate([XmlElement({
		localName: XmlSignature.ElementNames.X509Data
	})], KeyInfoX509Data);

	var SPKIData = function (_KeyInfoClause5) {
		_inherits(SPKIData, _KeyInfoClause5);

		function SPKIData() {
			_classCallCheck(this, SPKIData);

			return _possibleConstructorReturn(this, (SPKIData.__proto__ || Object.getPrototypeOf(SPKIData)).apply(this, arguments));
		}

		_createClass(SPKIData, [{
			key: 'importKey',
			value: function importKey(key) {
				var _this131 = this;

				return Promise.resolve().then(function () {
					return Application.crypto.subtle.exportKey("spki", key);
				}).then(function (spki) {
					_this131.SPKIexp = new Uint8Array(spki);
					_this131.Key = key;
					return _this131;
				});
			}
		}, {
			key: 'exportKey',
			value: function exportKey(alg) {
				var _this132 = this;

				return Promise.resolve().then(function () {
					return Application.crypto.subtle.importKey("spki", _this132.SPKIexp, alg, true, ["verify"]);
				}).then(function (key) {
					_this132.Key = key;
					return key;
				});
			}
		}]);

		return SPKIData;
	}(KeyInfoClause);
	__decorate([XmlChildElement({
		localName: XmlSignature.ElementNames.SPKIexp,
		namespaceURI: XmlSignature.NamespaceURI,
		prefix: XmlSignature.DefaultPrefix,
		required: true,
		converter: XmlBase64Converter
	})], SPKIData.prototype, "SPKIexp", void 0);
	SPKIData = __decorate([XmlElement({
		localName: XmlSignature.ElementNames.SPKIData
	})], SPKIData);

	var SignatureAlgorithms = {};
	SignatureAlgorithms[RSA_PKCS1_SHA1_NAMESPACE] = RsaPkcs1Sha1;
	SignatureAlgorithms[RSA_PKCS1_SHA256_NAMESPACE] = RsaPkcs1Sha256;
	SignatureAlgorithms[RSA_PKCS1_SHA384_NAMESPACE] = RsaPkcs1Sha384;
	SignatureAlgorithms[RSA_PKCS1_SHA512_NAMESPACE] = RsaPkcs1Sha512;
	SignatureAlgorithms[ECDSA_SHA1_NAMESPACE] = EcdsaSha1;
	SignatureAlgorithms[ECDSA_SHA256_NAMESPACE] = EcdsaSha256;
	SignatureAlgorithms[ECDSA_SHA384_NAMESPACE] = EcdsaSha384;
	SignatureAlgorithms[ECDSA_SHA512_NAMESPACE] = EcdsaSha512;
	SignatureAlgorithms[HMAC_SHA1_NAMESPACE] = HmacSha1;
	SignatureAlgorithms[HMAC_SHA256_NAMESPACE] = HmacSha256;
	SignatureAlgorithms[HMAC_SHA384_NAMESPACE] = HmacSha384;
	SignatureAlgorithms[HMAC_SHA512_NAMESPACE] = HmacSha512;
	var HashAlgorithms = {};
	HashAlgorithms[SHA1_NAMESPACE] = Sha1;
	HashAlgorithms[SHA256_NAMESPACE] = Sha256;
	HashAlgorithms[SHA384_NAMESPACE] = Sha384;
	HashAlgorithms[SHA512_NAMESPACE] = Sha512;

	var CryptoConfig = function () {
		function CryptoConfig() {
			_classCallCheck(this, CryptoConfig);
		}

		_createClass(CryptoConfig, null, [{
			key: 'CreateFromName',
			value: function CreateFromName(name) {
				var transform = void 0;
				switch (name) {
					case XmlSignature.AlgorithmNamespaces.XmlDsigBase64Transform:
						transform = new XmlDsigBase64Transform();
						break;
					case XmlSignature.AlgorithmNamespaces.XmlDsigC14NTransform:
						transform = new XmlDsigC14NTransform();
						break;
					case XmlSignature.AlgorithmNamespaces.XmlDsigC14NWithCommentsTransform:
						transform = new XmlDsigC14NWithCommentsTransform();
						break;
					case XmlSignature.AlgorithmNamespaces.XmlDsigEnvelopedSignatureTransform:
						transform = new XmlDsigEnvelopedSignatureTransform();
						break;
					case XmlSignature.AlgorithmNamespaces.XmlDsigXPathTransform:
						throw new XmlError(XE.ALGORITHM_NOT_SUPPORTED, name);

					case XmlSignature.AlgorithmNamespaces.XmlDsigXsltTransform:
						throw new XmlError(XE.ALGORITHM_NOT_SUPPORTED, name);

					case XmlSignature.AlgorithmNamespaces.XmlDsigExcC14NTransform:
						transform = new XmlDsigExcC14NTransform();
						break;
					case XmlSignature.AlgorithmNamespaces.XmlDsigExcC14NWithCommentsTransform:
						transform = new XmlDsigExcC14NWithCommentsTransform();
						break;
					case XmlSignature.AlgorithmNamespaces.XmlDecryptionTransform:
						throw new XmlError(XE.ALGORITHM_NOT_SUPPORTED, name);

					default:
						throw new XmlError(XE.ALGORITHM_NOT_SUPPORTED, name);
				}
				return transform;
			}
		}, {
			key: 'CreateSignatureAlgorithm',
			value: function CreateSignatureAlgorithm(method) {
				var alg = SignatureAlgorithms[method.Algorithm] || null;
				if (alg) {
					return new alg();
				} else if (method.Algorithm === RSA_PSS_WITH_PARAMS_NAMESPACE) {
					var pssParams = void 0;
					method.Any.Some(function (item) {
						if (item instanceof PssAlgorithmParams) {
							pssParams = item;
						}
						return !!pssParams;
					});
					if (pssParams) {
						switch (pssParams.DigestMethod.Algorithm) {
							case SHA1_NAMESPACE:
								return new RsaPssSha1(pssParams.SaltLength);
							case SHA256_NAMESPACE:
								return new RsaPssSha256(pssParams.SaltLength);
							case SHA384_NAMESPACE:
								return new RsaPssSha384(pssParams.SaltLength);
							case SHA512_NAMESPACE:
								return new RsaPssSha512(pssParams.SaltLength);
						}
					}
					throw new XmlError(XE.CRYPTOGRAPHIC, 'Cannot get params for RSA-PSS algoriithm');
				}
				throw new Error('signature algorithm \'' + method.Algorithm + '\' is not supported');
			}
		}, {
			key: 'CreateHashAlgorithm',
			value: function CreateHashAlgorithm(namespace) {
				var alg = HashAlgorithms[namespace];
				if (alg) {
					return new alg();
				} else {
					throw new Error("hash algorithm '" + namespace + "' is not supported");
				}
			}
		}, {
			key: 'GetHashAlgorithm',
			value: function GetHashAlgorithm(algorithm) {
				var alg = typeof algorithm === "string" ? { name: algorithm } : algorithm;
				switch (alg.name.toUpperCase()) {
					case SHA1:
						return new Sha1();
					case SHA256:
						return new Sha256();
					case SHA384:
						return new Sha384();
					case SHA512:
						return new Sha512();
					default:
						throw new XmlError(XE.ALGORITHM_NOT_SUPPORTED, alg.name);
				}
			}
		}, {
			key: 'GetSignatureAlgorithm',
			value: function GetSignatureAlgorithm(algorithm) {
				if (typeof algorithm.hash === "string") {
					algorithm.hash = {
						name: algorithm.hash
					};
				}
				var hashName = algorithm.hash.name;
				if (!hashName) {
					throw new Error("Signing algorithm doesn't have name for hash");
				}
				var alg = void 0;
				switch (algorithm.name.toUpperCase()) {
					case RSA_PKCS1.toUpperCase():
						switch (hashName.toUpperCase()) {
							case SHA1:
								alg = new RsaPkcs1Sha1();
								break;
							case SHA256:
								alg = new RsaPkcs1Sha256();
								break;
							case SHA384:
								alg = new RsaPkcs1Sha384();
								break;
							case SHA512:
								alg = new RsaPkcs1Sha512();
								break;
							default:
								throw new XmlError(XE.ALGORITHM_NOT_SUPPORTED, algorithm.name + ':' + hashName);
						}
						break;
					case RSA_PSS.toUpperCase():
						var saltLength = algorithm.saltLength;
						switch (hashName.toUpperCase()) {
							case SHA1:
								alg = new RsaPssSha1(saltLength);
								break;
							case SHA256:
								alg = new RsaPssSha256(saltLength);
								break;
							case SHA384:
								alg = new RsaPssSha384(saltLength);
								break;
							case SHA512:
								alg = new RsaPssSha512(saltLength);
								break;
							default:
								throw new XmlError(XE.ALGORITHM_NOT_SUPPORTED, algorithm.name + ':' + hashName);
						}
						break;
					case ECDSA:
						switch (hashName.toUpperCase()) {
							case SHA1:
								alg = new EcdsaSha1();
								break;
							case SHA256:
								alg = new EcdsaSha256();
								break;
							case SHA384:
								alg = new EcdsaSha384();
								break;
							case SHA512:
								alg = new EcdsaSha512();
								break;
							default:
								throw new XmlError(XE.ALGORITHM_NOT_SUPPORTED, algorithm.name + ':' + hashName);
						}
						break;
					case HMAC:
						switch (hashName.toUpperCase()) {
							case SHA1:
								alg = new HmacSha1();
								break;
							case SHA256:
								alg = new HmacSha256();
								break;
							case SHA384:
								alg = new HmacSha384();
								break;
							case SHA512:
								alg = new HmacSha512();
								break;
							default:
								throw new XmlError(XE.ALGORITHM_NOT_SUPPORTED, algorithm.name + ':' + hashName);
						}
						break;
					default:
						throw new XmlError(XE.ALGORITHM_NOT_SUPPORTED, algorithm.name);
				}
				return alg;
			}
		}]);

		return CryptoConfig;
	}();

	var SignedXml = function () {
		function SignedXml(node) {
			_classCallCheck(this, SignedXml);

			this.signature = new Signature$$1();

			if (node && node.nodeType === XmlNodeType.Document) {
				this.document = node;
			} else if (node && node.nodeType === XmlNodeType.Element) {
				var xmlText = new XMLSerializer().serializeToString(node);
				this.document = new DOMParser().parseFromString(xmlText, APPLICATION_XML);
			}
		}

		_createClass(SignedXml, [{
			key: 'Sign',
			value: function Sign(algorithm, key, data, options) {
				var _this133 = this;

				var alg = void 0;
				var signedInfo = void 0;
				return Promise.resolve().then(function () {
					var signingAlg = assign$1({}, key.algorithm, algorithm);
					alg = CryptoConfig.GetSignatureAlgorithm(signingAlg);
					return _this133.ApplySignOptions(_this133.XmlSignature, algorithm, key, options);
				}).then(function () {
					signedInfo = _this133.XmlSignature.SignedInfo;
					return _this133.DigestReferences(data.documentElement);
				}).then(function () {
					signedInfo.SignatureMethod.Algorithm = alg.namespaceURI;
					if (RSA_PSS.toUpperCase() === algorithm.name.toUpperCase()) {
						var alg2 = assign$1({}, key.algorithm, algorithm);
						if (typeof alg2.hash === "string") {
							alg2.hash = { name: alg2.hash };
						}
						var params = new PssAlgorithmParams(alg2);
						_this133.XmlSignature.SignedInfo.SignatureMethod.Any.Add(params);
					} else if (HMAC.toUpperCase() === algorithm.name.toUpperCase()) {
						var outputLength = 0;
						var hmacAlg = key.algorithm;
						switch (hmacAlg.hash.name.toUpperCase()) {
							case SHA1:
								outputLength = hmacAlg.length || 160;
								break;
							case SHA256:
								outputLength = hmacAlg.length || 256;
								break;
							case SHA384:
								outputLength = hmacAlg.length || 384;
								break;
							case SHA512:
								outputLength = hmacAlg.length || 512;
								break;
						}
						_this133.XmlSignature.SignedInfo.SignatureMethod.HMACOutputLength = outputLength;
					}
					var si = _this133.TransformSignedInfo(data);
					return alg.Sign(si, key, algorithm);
				}).then(function (signature) {
					_this133.Key = key;
					_this133.XmlSignature.SignatureValue = new Uint8Array(signature);
					_this133.document = data;
					return _this133.XmlSignature;
				});
			}
		}, {
			key: 'Verify',
			value: function Verify(key) {
				var _this134 = this;

				return Promise.resolve().then(function () {
					var xml = _this134.document;
					if (!(xml && xml.documentElement)) {
						throw new XmlError(XE.NULL_PARAM, "SignedXml", "document");
					}
					return _this134.ValidateReferences(xml.documentElement);
				}).then(function (res) {
					if (res) {
						var promise = Promise.resolve([]);
						if (key) {
							promise = promise.then(function () {
								return [key];
							});
						} else {
							promise = promise.then(function () {
								return _this134.GetPublicKeys();
							});
						}
						return promise.then(function (keys) {
							return _this134.ValidateSignatureValue(keys);
						});
					} else {
						return false;
					}
				});
			}
		}, {
			key: 'GetXml',
			value: function GetXml() {
				return this.signature.GetXml();
			}
		}, {
			key: 'LoadXml',
			value: function LoadXml(value) {
				this.signature = Signature$$1.LoadXml(value);
			}
		}, {
			key: 'toString',
			value: function toString() {
				var signature = this.XmlSignature;
				var enveloped = signature.SignedInfo.References && signature.SignedInfo.References.Some(function (r) {
					return r.Transforms && r.Transforms.Some(function (t) {
						return t instanceof XmlDsigEnvelopedSignatureTransform;
					});
				});
				if (enveloped) {
					var doc = this.document.documentElement.cloneNode(true);
					var _node2 = this.XmlSignature.GetXml();
					if (!_node2) {
						throw new XmlError(XE.XML_EXCEPTION, "Cannot get Xml element from Signature");
					}
					var sig = _node2.cloneNode(true);
					doc.appendChild(sig);
					return new XMLSerializer().serializeToString(doc);
				}
				return this.XmlSignature.toString();
			}
		}, {
			key: 'GetPublicKeys',
			value: function GetPublicKeys() {
				var _this135 = this;

				var keys = [];
				return Promise.resolve().then(function () {
					var pkEnumerator = _this135.XmlSignature.KeyInfo.GetIterator();
					var promises = [];
					pkEnumerator.forEach(function (kic) {
						var alg = CryptoConfig.CreateSignatureAlgorithm(_this135.XmlSignature.SignedInfo.SignatureMethod);
						if (kic instanceof KeyInfoX509Data) {
							kic.Certificates.forEach(function (cert) {
								promises.push(cert.exportKey(alg.algorithm).then(function (key) {
									keys.push(key);
								}));
							});
						} else {
							promises.push(kic.exportKey(alg.algorithm).then(function (key) {
								keys.push(key);
							}));
						}
					});
					return Promise.all(promises);
				}).then(function () {
					return keys;
				});
			}
		}, {
			key: 'GetSignatureNamespaces',
			value: function GetSignatureNamespaces() {
				var namespaces = {};
				if (this.XmlSignature.NamespaceURI) {
					namespaces[this.XmlSignature.Prefix || ""] = this.XmlSignature.NamespaceURI;
				}
				return namespaces;
			}
		}, {
			key: 'CopyNamespaces',
			value: function CopyNamespaces(src, dst, ignoreDefault) {
				this.InjectNamespaces(SelectRootNamespaces(src), dst, ignoreDefault);
			}
		}, {
			key: 'InjectNamespaces',
			value: function InjectNamespaces(namespaces, target, ignoreDefault) {
				for (var i in namespaces) {
					var uri = namespaces[i];
					if (ignoreDefault && i === "") {
						continue;
					}
					target.setAttribute("xmlns" + (i ? ":" + i : ""), uri);
				}
			}
		}, {
			key: 'DigestReference',
			value: function DigestReference(doc, reference, checkHmac) {
				var _this136 = this;

				return Promise.resolve().then(function () {
					if (reference.Uri) {
						var objectName = void 0;
						if (!reference.Uri.indexOf("#xpointer")) {
							var uri = reference.Uri;
							uri = uri.substring(9).replace(/[\r\n\t\s]/g, "");
							if (uri.length < 2 || uri[0] !== '(' || uri[uri.length - 1] !== ')') {
								uri = "";
							} else {
								uri = uri.substring(1, uri.length - 1);
							}
							if (uri.length > 6 && uri.indexOf('id(') === 0 && uri[uri.length - 1] === ')') {
								objectName = uri.substring(4, uri.length - 2);
							}
						} else if (reference.Uri[0] === '#') {
							objectName = reference.Uri.substring(1);
						}
						if (objectName) {
							var found = null;
							if (_this136.XmlSignature.ObjectList) {
								_this136.XmlSignature.ObjectList.Some(function (obj) {
									found = findById(obj.GetXml(), objectName);
									if (found) {
										var el = found.cloneNode(true);

										_this136.CopyNamespaces(doc, el, false);

										if (_this136.Parent) {
											var parent = _this136.Parent instanceof XmlObject ? _this136.Parent.GetXml() : _this136.Parent;
											_this136.CopyNamespaces(parent, el, true);
										}
										_this136.CopyNamespaces(found, el, false);
										_this136.InjectNamespaces(_this136.GetSignatureNamespaces(), el, true);
										doc = el;
										return true;
									}
									return false;
								});
							}
							if (!found && doc) {
								found = XmlObject.GetElementById(doc, objectName);
								if (found) {
									var el = found.cloneNode(true);
									_this136.CopyNamespaces(found, el, false);
									_this136.CopyNamespaces(doc, el, false);
									doc = el;
								}
							}
							if (found == null) {
								throw new XmlError(XE.CRYPTOGRAPHIC, 'Cannot get object by reference: ' + objectName);
							}
						}
					}
					var canonOutput = null;
					if (reference.Transforms && reference.Transforms.Count) {
						canonOutput = _this136.ApplyTransforms(reference.Transforms, doc);
					} else {
						if (reference.Uri && reference.Uri[0] !== '#') {
							canonOutput = new XMLSerializer().serializeToString(doc.ownerDocument);
						} else {
							var excC14N = new XmlDsigC14NTransform();
							excC14N.LoadInnerXml(doc);
							canonOutput = excC14N.GetOutput();
						}
					}
					if (!reference.DigestMethod.Algorithm) {
						throw new XmlError(XE.NULL_PARAM, "Reference", "DigestMethod");
					}
					var digest = CryptoConfig.CreateHashAlgorithm(reference.DigestMethod.Algorithm);
					return digest.Digest(canonOutput);
				});
			}
		}, {
			key: 'DigestReferences',
			value: function DigestReferences(data) {
				var _this137 = this;

				return Promise.resolve().then(function () {
					var promises = _this137.XmlSignature.SignedInfo.References.Map(function (ref) {
						if (!ref.DigestMethod.Algorithm) {
							ref.DigestMethod.Algorithm = new Sha256().namespaceURI;
						}
						return _this137.DigestReference(data, ref, false).then(function (hashValue) {
							ref.DigestValue = hashValue;
						});
					}).GetIterator();
					return Promise.all(promises);
				});
			}
		}, {
			key: 'TransformSignedInfo',
			value: function TransformSignedInfo(data) {
				var t = CryptoConfig.CreateFromName(this.XmlSignature.SignedInfo.CanonicalizationMethod.Algorithm);
				var xml = this.XmlSignature.SignedInfo.GetXml();
				if (!xml) {
					throw new XmlError(XE.XML_EXCEPTION, "Cannot get Xml element from SignedInfo");
				}
				var node = xml.cloneNode(true);

				this.CopyNamespaces(xml, node, false);
				if (data) {
					if (data.nodeType === XmlNodeType.Document) {
						this.CopyNamespaces(data.documentElement, node, false);
					} else {
						this.CopyNamespaces(data, node, false);
					}
				}
				if (this.Parent) {
					var parentXml = this.Parent instanceof XmlObject ? this.Parent.GetXml() : this.Parent;
					if (parentXml) {
						this.CopyNamespaces(parentXml, node, false);
					}
				}

				var childNamespaces = SelectNamespaces(xml);
				for (var i in childNamespaces) {
					var uri = childNamespaces[i];
					if (i === node.prefix) {
						continue;
					}
					node.setAttribute("xmlns" + (i ? ":" + i : ""), uri);
				}
				t.LoadInnerXml(node);
				var res = t.GetOutput();
				return res;
			}
		}, {
			key: 'ResolveTransform',
			value: function ResolveTransform(transform) {
				switch (transform) {
					case "enveloped":
						return new XmlDsigEnvelopedSignatureTransform();
					case "c14n":
						return new XmlDsigC14NTransform();
					case "c14n-com":
						return new XmlDsigC14NWithCommentsTransform();
					case "exc-c14n":
						return new XmlDsigExcC14NTransform();
					case "exc-c14n-com":
						return new XmlDsigExcC14NWithCommentsTransform();
					case "base64":
						return new XmlDsigBase64Transform();
					default:
						throw new XmlError(XE.CRYPTOGRAPHIC_UNKNOWN_TRANSFORM, transform);
				}
			}
		}, {
			key: 'ApplyTransforms',
			value: function ApplyTransforms(transforms, input) {
				var output = null;

				transforms.Sort(function (a, b) {
					if (b instanceof XmlDsigEnvelopedSignatureTransform) {
						return 1;
					}
					return 0;
				}).ForEach(function (transform) {
					if (transform instanceof XmlDsigC14NWithCommentsTransform) {
						transform = new XmlDsigC14NTransform();
					}
					if (transform instanceof XmlDsigExcC14NWithCommentsTransform) {
						transform = new XmlDsigExcC14NTransform();
					}
					transform.LoadInnerXml(input);
					output = transform.GetOutput();
				});

				if (transforms.Count === 1 && transforms.Item(0) instanceof XmlDsigEnvelopedSignatureTransform) {
					var c14n = new XmlDsigC14NTransform();
					c14n.LoadInnerXml(input);
					output = c14n.GetOutput();
				}
				return output;
			}
		}, {
			key: 'ApplySignOptions',
			value: function ApplySignOptions(signature, algorithm, key) {
				var _this138 = this;

				var options = arguments.length > 3 && arguments[3] !== undefined ? arguments[3] : {};

				return Promise.resolve().then(function () {
					if (options.keyValue && key.algorithm.name.toUpperCase() !== HMAC) {
						if (!signature.KeyInfo) {
							signature.KeyInfo = new KeyInfo();
						}
						var keyInfo = signature.KeyInfo;
						var keyValue = new KeyValue();
						keyInfo.Add(keyValue);
						return keyValue.importKey(options.keyValue);
					} else {
						return Promise.resolve();
					}
				}).then(function () {
					if (options.x509) {
						if (!signature.KeyInfo) {
							signature.KeyInfo = new KeyInfo();
						}
						var keyInfo = signature.KeyInfo;
						options.x509.forEach(function (x509) {
							var raw = Convert.FromBase64(x509);
							var x509Data = new KeyInfoX509Data(raw);
							keyInfo.Add(x509Data);
						});
					}
					return Promise.resolve();
				}).then(function () {
					if (options.references) {
						options.references.forEach(function (item) {
							var reference = new Reference();

							if (item.id) {
								reference.Id = item.id;
							}

							if (item.uri) {
								reference.Uri = item.uri;
							}

							if (item.type) {
								reference.Type = item.type;
							}

							var digestAlgorithm = CryptoConfig.GetHashAlgorithm(item.hash);
							reference.DigestMethod.Algorithm = digestAlgorithm.namespaceURI;

							if (item.transforms && item.transforms.length) {
								var transforms = new Transforms();
								item.transforms.forEach(function (transform) {
									transforms.Add(_this138.ResolveTransform(transform));
								});
								reference.Transforms = transforms;
							}
							if (!signature.SignedInfo.References) {
								signature.SignedInfo.References = new References();
							}
							signature.SignedInfo.References.Add(reference);
						});
					}

					if (!signature.SignedInfo.References.Count) {
						var reference = new Reference();
						signature.SignedInfo.References.Add(reference);
					}
					return Promise.resolve();
				});
			}
		}, {
			key: 'ValidateReferences',
			value: function ValidateReferences(doc) {
				var _this139 = this;

				return Promise.resolve().then(function () {
					return Promise.all(_this139.XmlSignature.SignedInfo.References.Map(function (ref) {
						return _this139.DigestReference(doc, ref, false).then(function (digest) {
							var b64Digest = Convert.ToBase64(digest);
							var b64DigestValue = Convert.ToString(ref.DigestValue, "base64");
							if (b64Digest !== b64DigestValue) {
								var errText = 'Invalid digest for uri \'' + ref.Uri + '\'. Calculated digest is ' + b64Digest + ' but the xml to validate supplies digest ' + b64DigestValue;
								throw new XmlError(XE.CRYPTOGRAPHIC, errText);
							}
							return Promise.resolve(true);
						});
					}).GetIterator());
				}).then(function () {
					return true;
				});
			}
		}, {
			key: 'ValidateSignatureValue',
			value: function ValidateSignatureValue(keys) {
				var _this140 = this;

				var signer = void 0;
				var signedInfoCanon = void 0;
				return Promise.resolve().then(function () {
					signedInfoCanon = _this140.TransformSignedInfo(_this140.document);
					signer = CryptoConfig.CreateSignatureAlgorithm(_this140.XmlSignature.SignedInfo.SignatureMethod);

					var chain = Promise.resolve(false);
					keys.forEach(function (key) {
						chain = chain.then(function (v) {
							if (!v) {
								return signer.Verify(signedInfoCanon, key, _this140.Signature);
							}
							return Promise.resolve(v);
						});
					});
					return chain;
				});
			}
		}, {
			key: 'XmlSignature',
			get: function get() {
				return this.signature;
			}
		}, {
			key: 'Signature',
			get: function get() {
				return this.XmlSignature.SignatureValue;
			}
		}]);

		return SignedXml;
	}();

	function findById(element, id) {
		if (element.nodeType !== XmlNodeType.Element) {
			return null;
		}
		if (element.hasAttribute("Id") && element.getAttribute("Id") === id) {
			return element;
		}
		if (element.childNodes && element.childNodes.length) {
			for (var i = 0; i < element.childNodes.length; i++) {
				var el = findById(element.childNodes[i], id);
				if (el) {
					return el;
				}
			}
		}
		return null;
	}

	function addNamespace(selectedNodes, name, namespace) {
		if (!(name in selectedNodes)) {
			selectedNodes[name] = namespace;
		}
	}

	function _SelectRootNamespaces(node) {
		var selectedNodes = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : {};

		if (node && node.nodeType === XmlNodeType.Element) {
			var el = node;
			if (el.namespaceURI && el.namespaceURI !== "http://www.w3.org/XML/1998/namespace") {
				addNamespace(selectedNodes, el.prefix ? el.prefix : "", node.namespaceURI);
			}

			for (var i = 0; i < el.attributes.length; i++) {
				var attr = el.attributes.item(i);
				if (attr.prefix === "xmlns") {
					addNamespace(selectedNodes, attr.localName ? attr.localName : "", attr.value);
				}
			}

			if (node.parentNode) {
				_SelectRootNamespaces(node.parentNode, selectedNodes);
			}
		}
	}
	function SelectRootNamespaces(node) {
		var attrs = {};
		_SelectRootNamespaces(node, attrs);
		return attrs;
	}

	var DigestAlgAndValueType = function (_super) {
		__extends(DigestAlgAndValueType, _super);
		function DigestAlgAndValueType() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		__decorate([XmlChildElement({
			parser: DigestMethod,
			required: true
		})], DigestAlgAndValueType.prototype, "DigestMethod", void 0);
		__decorate([XmlChildElement({
			localName: XmlSignature.ElementNames.DigestValue,
			namespaceURI: XmlSignature.NamespaceURI,
			prefix: XmlSignature.DefaultPrefix,
			converter: XmlBase64Converter,
			required: true
		})], DigestAlgAndValueType.prototype, "DigestValue", void 0);
		DigestAlgAndValueType = __decorate([XmlElement({ localName: XmlXades.ElementNames.DigestAlgAndValue })], DigestAlgAndValueType);
		return DigestAlgAndValueType;
	}(XadesObject);
	var IssuerSerial = function (_super) {
		__extends(IssuerSerial, _super);
		function IssuerSerial() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		IssuerSerial = __decorate([XmlElement({ localName: XmlXades.ElementNames.IssuerSerial, namespaceURI: XmlXades.NamespaceURI, prefix: XmlXades.DefaultPrefix })], IssuerSerial);
		return IssuerSerial;
	}(X509IssuerSerial);
	var Cert = function (_super) {
		__extends(Cert, _super);
		function Cert() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		__decorate([XmlChildElement({ localName: XmlXades.ElementNames.CertDigest, parser: DigestAlgAndValueType, required: true })], Cert.prototype, "CertDigest", void 0);
		__decorate([XmlChildElement({ parser: IssuerSerial, required: true })], Cert.prototype, "IssuerSerial", void 0);
		__decorate([XmlAttribute({ localName: XmlXades.AttributeNames.URI })], Cert.prototype, "Uri", void 0);
		Cert = __decorate([XmlElement({ localName: XmlXades.ElementNames.Cert })], Cert);
		return Cert;
	}(XadesObject);
	var CertIDList = function (_super) {
		__extends(CertIDList, _super);
		function CertIDList() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		CertIDList = __decorate([XmlElement({ localName: "CertIDList", parser: Cert })], CertIDList);
		return CertIDList;
	}(XadesCollection);
	var SigningCertificate = function (_super) {
		__extends(SigningCertificate, _super);
		function SigningCertificate() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		SigningCertificate = __decorate([XmlElement({ localName: XmlXades.ElementNames.SigningCertificate })], SigningCertificate);
		return SigningCertificate;
	}(CertIDList);

	var CompleteCertificateRefs = function (_super) {
		__extends(CompleteCertificateRefs, _super);
		function CompleteCertificateRefs() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		__decorate([XmlAttribute({ localName: XmlXades.AttributeNames.Id, defaultValue: "" })], CompleteCertificateRefs.prototype, "Id", void 0);
		__decorate([XmlChildElement({ localName: XmlXades.ElementNames.CertRefs, parser: CertIDList, required: true })], CompleteCertificateRefs.prototype, "CertRefs", void 0);
		CompleteCertificateRefs = __decorate([XmlElement({ localName: XmlXades.ElementNames.CompleteCertificateRefs })], CompleteCertificateRefs);
		return CompleteCertificateRefs;
	}(XadesObject);

	var token = /d{1,4}|m{1,4}|yy(?:yy)?|([HhMsTt])\1?|[LloSZWN]|"[^"]*"|'[^']*'/g;
	var timezone = /\b(?:[PMCEA][SDP]T|(?:Pacific|Mountain|Central|Eastern|Atlantic) (?:Standard|Daylight|Prevailing) Time|(?:GMT|UTC)(?:[-+]\d{4})?)\b/g;
	var timezoneClip = /[^-+\dA-Z]/g;
	function dateFormat(date, mask, utc, gmt) {
		if (arguments.length === 1 && kindOf(date) === "string" && !/\d/.test(date)) {
			mask = date;
			date = undefined;
		}
		date = date || new Date();
		if (!(date instanceof Date)) {
			date = new Date(date);
		}
		if (isNaN(date)) {
			throw TypeError("Invalid date");
		}
		mask = String(masks[mask] || mask || masks.default);

		var maskSlice = mask.slice(0, 4);
		if (maskSlice === "UTC:" || maskSlice === "GMT:") {
			mask = mask.slice(4);
			utc = true;
			if (maskSlice === "GMT:") {
				gmt = true;
			}
		}
		var _ = utc ? "getUTC" : "get";
		var d = date[_ + "Date"]();
		var D = date[_ + "Day"]();
		var m = date[_ + "Month"]();
		var y = date[_ + "FullYear"]();
		var H = date[_ + "Hours"]();
		var M = date[_ + "Minutes"]();
		var s = date[_ + "Seconds"]();
		var L = date[_ + "Milliseconds"]();
		var o = utc ? 0 : date.getTimezoneOffset();
		var W = getWeek(date);
		var N = getDayOfWeek(date);
		var flags = {
			d: d,
			dd: pad(d),
			ddd: i18n.dayNames[D],
			dddd: i18n.dayNames[D + 7],
			m: m + 1,
			mm: pad(m + 1),
			mmm: i18n.monthNames[m],
			mmmm: i18n.monthNames[m + 12],
			yy: String(y).slice(2),
			yyyy: y,
			h: H % 12 || 12,
			hh: pad(H % 12 || 12),
			H: H,
			HH: pad(H),
			M: M,
			MM: pad(M),
			s: s,
			ss: pad(s),
			l: pad(L, 3),
			L: pad(Math.round(L / 10)),
			t: H < 12 ? i18n.timeNames[0] : i18n.timeNames[1],
			tt: H < 12 ? i18n.timeNames[2] : i18n.timeNames[3],
			T: H < 12 ? i18n.timeNames[4] : i18n.timeNames[5],
			TT: H < 12 ? i18n.timeNames[6] : i18n.timeNames[7],
			Z: gmt ? "GMT" : utc ? "UTC" : (String(date).match(timezone) || [""]).pop().replace(timezoneClip, ""),
			o: (o > 0 ? "-" : "+") + pad(Math.floor(Math.abs(o) / 60) * 100 + Math.abs(o) % 60, 4),

			W: W,
			N: N
		};
		return mask.replace(token, function (match) {
			if (match in flags) {
				return flags[match];
			}
			return match.slice(1, match.length - 1);
		});
	}
	var masks = {
		default: "ddd mmm dd yyyy HH:MM:ss",
		shortDate: "m/d/yy",
		mediumDate: "mmm d, yyyy",
		longDate: "mmmm d, yyyy",
		fullDate: "dddd, mmmm d, yyyy",
		shortTime: "h:MM TT",
		mediumTime: "h:MM:ss TT",
		longTime: "h:MM:ss TT Z",
		isoDate: "yyyy-mm-dd",
		isoTime: "HH:MM:ss",
		isoDateTime: "yyyy-mm-dd'T'HH:MM:sso",
		isoUtcDateTime: "UTC:yyyy-mm-dd'T'HH:MM:ss'Z'",
		expiresHeaderFormat: "ddd, dd mmm yyyy HH:MM:ss Z"
	};

	var i18n = {
		dayNames: ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"],
		monthNames: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"],
		timeNames: ["a", "p", "am", "pm", "A", "P", "AM", "PM"]
	};
	function pad(val, len) {
		if (len === void 0) {
			len = 2;
		}
		val = String(val);
		while (val.length < len) {
			val = "0" + val;
		}
		return val;
	}

	function getWeek(date) {
		var targetThursday = new Date(date.getFullYear(), date.getMonth(), date.getDate());

		targetThursday.setDate(targetThursday.getDate() - (targetThursday.getDay() + 6) % 7 + 3);

		var firstThursday = new Date(targetThursday.getFullYear(), 0, 4);

		firstThursday.setDate(firstThursday.getDate() - (firstThursday.getDay() + 6) % 7 + 3);

		var ds = targetThursday.getTimezoneOffset() - firstThursday.getTimezoneOffset();
		targetThursday.setHours(targetThursday.getHours() - ds);

		var weekDiff = (targetThursday.getTime() - firstThursday.getTime()) / (86400000 * 7);
		return 1 + Math.floor(weekDiff);
	}

	function getDayOfWeek(date) {
		var dow = date.getDay();
		if (dow === 0) {
			dow = 7;
		}
		return dow;
	}

	function kindOf(val) {
		if (val === null) {
			return "null";
		}
		if (val === undefined) {
			return "undefined";
		}
		if ((typeof val === 'undefined' ? 'undefined' : _typeof(val)) !== "object") {
			return typeof val === 'undefined' ? 'undefined' : _typeof(val);
		}
		if (Array.isArray(val)) {
			return "array";
		}
		return {}.toString.call(val).slice(8, -1).toLowerCase();
	}

	var XadesDateTime = function (_super) {
		__extends(XadesDateTime, _super);
		function XadesDateTime() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		XadesDateTime.prototype.OnLoadXml = function (e) {
			if (e.textContent) {
				this.Value = new Date(e.textContent);
			}
		};
		XadesDateTime.prototype.OnGetXml = function (e) {
			if (this.Format) {
				e.textContent = dateFormat(this.Value, this.Format);
			} else {
				e.textContent = this.Value.toISOString();
			}
		};
		__decorate([XmlContent({
			defaultValue: new Date(),
			required: true
		})], XadesDateTime.prototype, "Value", void 0);
		XadesDateTime = __decorate([XmlElement({
			localName: "XadesDateTime",
			namespaceURI: XmlXades.NamespaceURI,
			prefix: XmlXades.DefaultPrefix
		})], XadesDateTime);
		return XadesDateTime;
	}(XadesObject);

	var OtherRef = function (_super) {
		__extends(OtherRef, _super);
		function OtherRef() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		OtherRef = __decorate([XmlElement({ localName: XmlXades.ElementNames.OtherRef })], OtherRef);
		return OtherRef;
	}(Any);
	var OtherRefs = function (_super) {
		__extends(OtherRefs, _super);
		function OtherRefs() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		OtherRefs = __decorate([XmlElement({ localName: XmlXades.ElementNames.OtherRefs })], OtherRefs);
		return OtherRefs;
	}(XadesCollection);
	var ResponderID = function (_super) {
		__extends(ResponderID, _super);
		function ResponderID() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		__decorate([XmlChildElement({
			localName: XmlXades.ElementNames.ByName,
			namespaceURI: XmlXades.NamespaceURI,
			prefix: XmlXades.DefaultPrefix,
			required: true
		})], ResponderID.prototype, "ByName", void 0);
		__decorate([XmlChildElement({
			localName: XmlXades.ElementNames.ByKey,
			namespaceURI: XmlXades.NamespaceURI,
			prefix: XmlXades.DefaultPrefix,
			converter: XmlBase64Converter,
			required: true
		})], ResponderID.prototype, "ByKey", void 0);
		ResponderID = __decorate([XmlElement({ localName: XmlXades.ElementNames.OCSPIdentifier })], ResponderID);
		return ResponderID;
	}(XadesObject);
	var OCSPIdentifier = function (_super) {
		__extends(OCSPIdentifier, _super);
		function OCSPIdentifier() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		__decorate([XmlAttribute({ localName: XmlXades.AttributeNames.URI, defaultValue: "" })], OCSPIdentifier.prototype, "URI", void 0);
		__decorate([XmlChildElement({ parser: ResponderID, required: true })], OCSPIdentifier.prototype, "ResponderID", void 0);
		__decorate([XmlChildElement({
			localName: XmlXades.ElementNames.IssueTime,
			parser: XadesDateTime,
			required: true
		})], OCSPIdentifier.prototype, "ProducedAt", void 0);
		OCSPIdentifier = __decorate([XmlElement({ localName: XmlXades.ElementNames.OCSPIdentifier })], OCSPIdentifier);
		return OCSPIdentifier;
	}(XadesObject);
	var OCSPRef = function (_super) {
		__extends(OCSPRef, _super);
		function OCSPRef() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		__decorate([XmlChildElement({ parser: OCSPIdentifier })], OCSPRef.prototype, "OCSPIdentifier", void 0);
		__decorate([XmlChildElement({ parser: DigestAlgAndValueType, required: true })], OCSPRef.prototype, "DigestAlgAndValue", void 0);
		OCSPRef = __decorate([XmlElement({ localName: XmlXades.ElementNames.OCSPRef })], OCSPRef);
		return OCSPRef;
	}(XadesObject);
	var OCSPRefs = function (_super) {
		__extends(OCSPRefs, _super);
		function OCSPRefs() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		OCSPRefs = __decorate([XmlElement({ localName: XmlXades.ElementNames.OCSPRefs })], OCSPRefs);
		return OCSPRefs;
	}(XadesCollection);
	var CRLIdentifier = function (_super) {
		__extends(CRLIdentifier, _super);
		function CRLIdentifier() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		__decorate([XmlAttribute({ localName: XmlXades.AttributeNames.URI, defaultValue: "" })], CRLIdentifier.prototype, "URI", void 0);
		__decorate([XmlChildElement({
			localName: XmlXades.ElementNames.Issuer,
			namespaceURI: XmlXades.NamespaceURI,
			prefix: XmlXades.DefaultPrefix,
			required: true
		})], CRLIdentifier.prototype, "Issuer", void 0);
		__decorate([XmlChildElement({
			localName: XmlXades.ElementNames.IssueTime,
			parser: XadesDateTime,
			required: true
		})], CRLIdentifier.prototype, "IssueTime", void 0);
		__decorate([XmlChildElement({
			localName: XmlXades.ElementNames.Number,
			namespaceURI: XmlXades.NamespaceURI,
			prefix: XmlXades.DefaultPrefix,
			converter: XmlNumberConverter
		})], CRLIdentifier.prototype, "Number", void 0);
		CRLIdentifier = __decorate([XmlElement({ localName: XmlXades.ElementNames.CRLIdentifier })], CRLIdentifier);
		return CRLIdentifier;
	}(XadesObject);
	var CRLRef = function (_super) {
		__extends(CRLRef, _super);
		function CRLRef() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		__decorate([XmlChildElement({ parser: DigestAlgAndValueType, required: true })], CRLRef.prototype, "DigestAlgAndValue", void 0);
		__decorate([XmlChildElement({ parser: CRLIdentifier })], CRLRef.prototype, "CRLIdentifier", void 0);
		CRLRef = __decorate([XmlElement({ localName: XmlXades.ElementNames.CRLRef })], CRLRef);
		return CRLRef;
	}(XadesObject);
	var CRLRefs = function (_super) {
		__extends(CRLRefs, _super);
		function CRLRefs() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		CRLRefs = __decorate([XmlElement({ localName: XmlXades.ElementNames.CRLRefs })], CRLRefs);
		return CRLRefs;
	}(XadesCollection);
	var CompleteRevocationRefs = function (_super) {
		__extends(CompleteRevocationRefs, _super);
		function CompleteRevocationRefs() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		__decorate([XmlAttribute({ localName: XmlXades.AttributeNames.Id, defaultValue: "" })], CompleteRevocationRefs.prototype, "Id", void 0);
		__decorate([XmlChildElement({ parser: CRLRefs })], CompleteRevocationRefs.prototype, "CRLRefs", void 0);
		__decorate([XmlChildElement({ parser: OCSPRefs })], CompleteRevocationRefs.prototype, "OCSPRefs", void 0);
		__decorate([XmlChildElement({ parser: OtherRefs })], CompleteRevocationRefs.prototype, "OtherRefs", void 0);
		CompleteRevocationRefs = __decorate([XmlElement({ localName: XmlXades.ElementNames.CompleteRevocationRefs })], CompleteRevocationRefs);
		return CompleteRevocationRefs;
	}(XadesObject);

	var CounterSignature = function (_super) {
		__extends(CounterSignature, _super);
		function CounterSignature() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		__decorate([XmlChildElement({ parser: Signature$$1, required: true })], CounterSignature.prototype, "Signature", void 0);
		CounterSignature = __decorate([XmlElement({ localName: XmlXades.ElementNames.CounterSignature })], CounterSignature);
		return CounterSignature;
	}(XadesObject);

	var DataObjectFormat = function (_super) {
		__extends(DataObjectFormat, _super);
		function DataObjectFormat() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		__decorate([XmlAttribute({ localName: XmlXades.AttributeNames.ObjectReference, required: true })], DataObjectFormat.prototype, "ObjectReference", void 0);
		__decorate([XmlChildElement({ localName: XmlXades.ElementNames.Description, namespaceURI: XmlXades.NamespaceURI, prefix: XmlXades.DefaultPrefix })], DataObjectFormat.prototype, "Description", void 0);
		__decorate([XmlChildElement({ parser: ObjectIdentifier })], DataObjectFormat.prototype, "ObjectIdentifier", void 0);
		__decorate([XmlChildElement({ localName: XmlXades.ElementNames.MimeType, namespaceURI: XmlXades.NamespaceURI, prefix: XmlXades.DefaultPrefix })], DataObjectFormat.prototype, "MimeType", void 0);
		__decorate([XmlChildElement({ localName: XmlXades.ElementNames.Encoding, namespaceURI: XmlXades.NamespaceURI, prefix: XmlXades.DefaultPrefix })], DataObjectFormat.prototype, "Encoding", void 0);
		DataObjectFormat = __decorate([XmlElement({ localName: XmlXades.ElementNames.DataObjectFormat })], DataObjectFormat);
		return DataObjectFormat;
	}(XadesObject);

	var Include = function (_super) {
		__extends(Include, _super);
		function Include() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		__decorate([XmlAttribute({
			localName: XmlXades.AttributeNames.URI,
			defaultValue: "",
			required: true
		})], Include.prototype, "Uri", void 0);
		__decorate([XmlAttribute({
			localName: XmlXades.AttributeNames.ReferencedData,
			defaultValue: false
		})], Include.prototype, "ReferencedData", void 0);
		Include = __decorate([XmlElement({
			localName: XmlXades.ElementNames.Include
		})], Include);
		return Include;
	}(XadesObject);
	var ReferenceInfo = function (_super) {
		__extends(ReferenceInfo, _super);
		function ReferenceInfo() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		__decorate([XmlAttribute({ localName: XmlXades.AttributeNames.URI, defaultValue: "" })], ReferenceInfo.prototype, "Uri", void 0);
		__decorate([XmlAttribute({ localName: XmlXades.AttributeNames.Id, defaultValue: "" })], ReferenceInfo.prototype, "Id", void 0);
		__decorate([XmlChildElement({
			localName: XmlSignature.ElementNames.DigestMethod,
			namespaceURI: XmlSignature.NamespaceURI,
			prefix: XmlSignature.DefaultPrefix,
			required: true
		})], ReferenceInfo.prototype, "DigestMethod", void 0);
		__decorate([XmlChildElement({
			localName: XmlSignature.ElementNames.DigestMethod,
			namespaceURI: XmlSignature.NamespaceURI,
			prefix: XmlSignature.DefaultPrefix,
			converter: XmlBase64Converter,
			required: true
		})], ReferenceInfo.prototype, "DigestValue", void 0);
		ReferenceInfo = __decorate([XmlElement({
			localName: XmlXades.ElementNames.ReferenceInfo
		})], ReferenceInfo);
		return ReferenceInfo;
	}(XadesObject);
	var ReferenceInfos = function (_super) {
		__extends(ReferenceInfos, _super);
		function ReferenceInfos() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		ReferenceInfos = __decorate([XmlElement({ localName: "ReferenceInfos", parser: ReferenceInfo })], ReferenceInfos);
		return ReferenceInfos;
	}(XadesCollection);
	var EncapsulatedTimeStamp = function (_super) {
		__extends(EncapsulatedTimeStamp, _super);
		function EncapsulatedTimeStamp() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		EncapsulatedTimeStamp = __decorate([XmlElement({ localName: XmlXades.ElementNames.EncapsulatedTimeStamp })], EncapsulatedTimeStamp);
		return EncapsulatedTimeStamp;
	}(EncapsulatedPKIData);
	var EncapsulatedTimeStampCollection = function (_super) {
		__extends(EncapsulatedTimeStampCollection, _super);
		function EncapsulatedTimeStampCollection() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		EncapsulatedTimeStampCollection = __decorate([XmlElement({ localName: "EncapsulatedPKIDatas", parser: EncapsulatedTimeStamp })], EncapsulatedTimeStampCollection);
		return EncapsulatedTimeStampCollection;
	}(XadesCollection);
	var XMLTimeStamp = function (_super) {
		__extends(XMLTimeStamp, _super);
		function XMLTimeStamp() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		XMLTimeStamp = __decorate([XmlElement({ localName: XmlXades.ElementNames.XMLTimeStamp })], XMLTimeStamp);
		return XMLTimeStamp;
	}(Any);
	var XMLTimeStampCollection = function (_super) {
		__extends(XMLTimeStampCollection, _super);
		function XMLTimeStampCollection() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		XMLTimeStampCollection = __decorate([XmlElement({ localName: "XMLTimeStampCollection", parser: XMLTimeStamp })], XMLTimeStampCollection);
		return XMLTimeStampCollection;
	}(XadesCollection);
	var GenericTimeStamp = function (_super) {
		__extends(GenericTimeStamp, _super);
		function GenericTimeStamp() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		__decorate([XmlAttribute({ localName: XmlXades.AttributeNames.Id, defaultValue: "" })], GenericTimeStamp.prototype, "Id", void 0);
		__decorate([XmlChildElement({ parser: Include })], GenericTimeStamp.prototype, "Include", void 0);
		__decorate([XmlChildElement({ parser: ReferenceInfos, noRoot: true })], GenericTimeStamp.prototype, "ReferenceInfo", void 0);
		__decorate([XmlChildElement({ parser: CanonicalizationMethod })], GenericTimeStamp.prototype, "CanonicalizationMethod", void 0);
		__decorate([XmlChildElement({ parser: EncapsulatedTimeStampCollection, noRoot: true })], GenericTimeStamp.prototype, "EncapsulatedTimeStamp", void 0);
		__decorate([XmlChildElement({ parser: XMLTimeStampCollection, noRoot: true })], GenericTimeStamp.prototype, "XMLTimeStamp", void 0);
		GenericTimeStamp = __decorate([XmlElement({
			localName: "GenericTimeStamp"
		})], GenericTimeStamp);
		return GenericTimeStamp;
	}(XadesObject);

	var OtherTimeStamp = function (_super) {
		__extends(OtherTimeStamp, _super);
		function OtherTimeStamp() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		__decorate([XmlAttribute({ localName: XmlXades.AttributeNames.Id, defaultValue: "" })], OtherTimeStamp.prototype, "Id", void 0);
		__decorate([XmlChildElement({ parser: ReferenceInfos, noRoot: true })], OtherTimeStamp.prototype, "ReferenceInfo", void 0);
		__decorate([XmlChildElement({ parser: CanonicalizationMethod })], OtherTimeStamp.prototype, "CanonicalizationMethod", void 0);
		__decorate([XmlChildElement({ parser: EncapsulatedTimeStampCollection, noRoot: true })], OtherTimeStamp.prototype, "EncapsulatedTimeStamp", void 0);
		__decorate([XmlChildElement({ parser: XMLTimeStampCollection, noRoot: true })], OtherTimeStamp.prototype, "XMLTimeStamp", void 0);
		OtherTimeStamp = __decorate([XmlElement({
			localName: XmlXades.ElementNames.OtherTimeStamp
		})], OtherTimeStamp);
		return OtherTimeStamp;
	}(XadesObject);

	var XAdESTimeStamp = function (_super) {
		__extends(XAdESTimeStamp, _super);
		function XAdESTimeStamp() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		__decorate([XmlAttribute({ localName: XmlXades.AttributeNames.Id, defaultValue: "" })], XAdESTimeStamp.prototype, "Id", void 0);
		__decorate([XmlChildElement({ parser: Include })], XAdESTimeStamp.prototype, "Include", void 0);
		__decorate([XmlChildElement({ parser: CanonicalizationMethod })], XAdESTimeStamp.prototype, "CanonicalizationMethod", void 0);
		__decorate([XmlChildElement({ parser: EncapsulatedTimeStampCollection, noRoot: true })], XAdESTimeStamp.prototype, "EncapsulatedTimeStamp", void 0);
		__decorate([XmlChildElement({ parser: XMLTimeStampCollection, noRoot: true })], XAdESTimeStamp.prototype, "XMLTimeStamp", void 0);
		XAdESTimeStamp = __decorate([XmlElement({
			localName: XmlXades.ElementNames.XAdESTimeStamp
		})], XAdESTimeStamp);
		return XAdESTimeStamp;
	}(XadesObject);

	var IndividualDataObjectsTimeStamp = function (_super) {
		__extends(IndividualDataObjectsTimeStamp, _super);
		function IndividualDataObjectsTimeStamp() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		IndividualDataObjectsTimeStamp = __decorate([XmlElement({ localName: XmlXades.ElementNames.IndividualDataObjectsTimeStamp })], IndividualDataObjectsTimeStamp);
		return IndividualDataObjectsTimeStamp;
	}(XAdESTimeStamp);
	var IndividualDataObjectsTimeStampCollection = function (_super) {
		__extends(IndividualDataObjectsTimeStampCollection, _super);
		function IndividualDataObjectsTimeStampCollection() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		IndividualDataObjectsTimeStampCollection = __decorate([XmlElement({ localName: "IndividualDataObjectsTimeStampCollection", parser: IndividualDataObjectsTimeStamp })], IndividualDataObjectsTimeStampCollection);
		return IndividualDataObjectsTimeStampCollection;
	}(XadesCollection);
	var AllDataObjectsTimeStamp = function (_super) {
		__extends(AllDataObjectsTimeStamp, _super);
		function AllDataObjectsTimeStamp() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		AllDataObjectsTimeStamp = __decorate([XmlElement({ localName: XmlXades.ElementNames.AllDataObjectsTimeStamp })], AllDataObjectsTimeStamp);
		return AllDataObjectsTimeStamp;
	}(XAdESTimeStamp);
	var DataObjectFormatCollection = function (_super) {
		__extends(DataObjectFormatCollection, _super);
		function DataObjectFormatCollection() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		DataObjectFormatCollection = __decorate([XmlElement({ localName: "DataObjectFormatCollection", parser: DataObjectFormat })], DataObjectFormatCollection);
		return DataObjectFormatCollection;
	}(XadesCollection);
	var CommitmentTypeIndicationCollection = function (_super) {
		__extends(CommitmentTypeIndicationCollection, _super);
		function CommitmentTypeIndicationCollection() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		CommitmentTypeIndicationCollection = __decorate([XmlElement({ localName: "CommitmentTypeIndicationCollection", parser: CommitmentTypeIndication })], CommitmentTypeIndicationCollection);
		return CommitmentTypeIndicationCollection;
	}(XadesCollection);
	var AllDataObjectsTimeStampCollection = function (_super) {
		__extends(AllDataObjectsTimeStampCollection, _super);
		function AllDataObjectsTimeStampCollection() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		AllDataObjectsTimeStampCollection = __decorate([XmlElement({ localName: "AllDataObjectsTimeStampCollection", parser: AllDataObjectsTimeStamp })], AllDataObjectsTimeStampCollection);
		return AllDataObjectsTimeStampCollection;
	}(XadesCollection);
	var SignedDataObjectProperties = function (_super) {
		__extends(SignedDataObjectProperties, _super);
		function SignedDataObjectProperties() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		__decorate([XmlAttribute({ localName: XmlXades.AttributeNames.Id, defaultValue: "" })], SignedDataObjectProperties.prototype, "Id", void 0);
		__decorate([XmlChildElement({ parser: DataObjectFormatCollection, noRoot: true })], SignedDataObjectProperties.prototype, "DataObjectFormats", void 0);
		__decorate([XmlChildElement({ parser: CommitmentTypeIndicationCollection, noRoot: true })], SignedDataObjectProperties.prototype, "CommitmentTypeIndications", void 0);
		__decorate([XmlChildElement({ parser: AllDataObjectsTimeStampCollection, noRoot: true })], SignedDataObjectProperties.prototype, "AllDataObjectsTimeStamps", void 0);
		__decorate([XmlChildElement({ parser: IndividualDataObjectsTimeStampCollection, noRoot: true })], SignedDataObjectProperties.prototype, "IndividualDataObjectsTimeStamps", void 0);
		SignedDataObjectProperties = __decorate([XmlElement({
			localName: XmlXades.ElementNames.SignedDataObjectProperties
		})], SignedDataObjectProperties);
		return SignedDataObjectProperties;
	}(XadesObject);

	var SigPolicyId = function (_super) {
		__extends(SigPolicyId, _super);
		function SigPolicyId() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		SigPolicyId = __decorate([XmlElement({ localName: XmlXades.ElementNames.SigPolicyId })], SigPolicyId);
		return SigPolicyId;
	}(ObjectIdentifier);
	var SigPolicyHash = function (_super) {
		__extends(SigPolicyHash, _super);
		function SigPolicyHash() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		SigPolicyHash = __decorate([XmlElement({ localName: XmlXades.ElementNames.SigPolicyHash })], SigPolicyHash);
		return SigPolicyHash;
	}(DigestAlgAndValueType);
	var SigPolicyQualifier = function (_super) {
		__extends(SigPolicyQualifier, _super);
		function SigPolicyQualifier() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		SigPolicyQualifier = __decorate([XmlElement({ localName: XmlXades.ElementNames.SigPolicyQualifier })], SigPolicyQualifier);
		return SigPolicyQualifier;
	}(AnyCollection);
	var Integer$1 = function (_super) {
		__extends(Integer, _super);
		function Integer() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		__decorate([XmlContent({ converter: XmlNumberConverter, required: true })], Integer.prototype, "Value", void 0);
		Integer = __decorate([XmlElement({ localName: "int" })], Integer);
		return Integer;
	}(XadesObject);
	var IntegerList = function (_super) {
		__extends(IntegerList, _super);
		function IntegerList() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		IntegerList = __decorate([XmlElement({ localName: "IntegerList", parser: Integer$1 })], IntegerList);
		return IntegerList;
	}(XadesCollection);
	var NoticeReference = function (_super) {
		__extends(NoticeReference, _super);
		function NoticeReference() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		__decorate([XmlChildElement({
			localName: XmlXades.ElementNames.Organization,
			namespaceURI: XmlXades.NamespaceURI,
			prefix: XmlXades.DefaultPrefix,
			required: true
		})], NoticeReference.prototype, "Organization", void 0);
		__decorate([XmlChildElement({ localName: XmlXades.ElementNames.NoticeNumbers, parser: IntegerList, required: true })], NoticeReference.prototype, "NoticeNumbers", void 0);
		NoticeReference = __decorate([XmlElement({ localName: XmlXades.ElementNames.NoticeRef })], NoticeReference);
		return NoticeReference;
	}(XadesObject);
	var SPUserNotice = function (_super) {
		__extends(SPUserNotice, _super);
		function SPUserNotice() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		__decorate([XmlChildElement({ localName: XmlXades.ElementNames.NoticeRef, parser: NoticeReference })], SPUserNotice.prototype, "NoticeRef", void 0);
		__decorate([XmlChildElement({
			localName: XmlXades.ElementNames.ExplicitText,
			namespaceURI: XmlXades.NamespaceURI,
			prefix: XmlXades.DefaultPrefix
		})], SPUserNotice.prototype, "ExplicitText", void 0);
		SPUserNotice = __decorate([XmlElement({ localName: XmlXades.ElementNames.SPUserNotice })], SPUserNotice);
		return SPUserNotice;
	}(XadesObject);
	var SPURI = function (_super) {
		__extends(SPURI, _super);
		function SPURI() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		__decorate([XmlContent()], SPURI.prototype, "Value", void 0);
		SPURI = __decorate([XmlElement({ localName: XmlXades.ElementNames.SPURI })], SPURI);
		return SPURI;
	}(XadesObject);
	var SigPolicyQualifiers = function (_super) {
		__extends(SigPolicyQualifiers, _super);
		function SigPolicyQualifiers() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		SigPolicyQualifiers = __decorate([XmlElement({ localName: XmlXades.ElementNames.SigPolicyQualifiers, parser: SigPolicyQualifier })], SigPolicyQualifiers);
		return SigPolicyQualifiers;
	}(XadesCollection);
	var SignaturePolicyId = function (_super) {
		__extends(SignaturePolicyId, _super);
		function SignaturePolicyId() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		__decorate([XmlChildElement({ localName: XmlXades.ElementNames.SigPolicyId, parser: SigPolicyId, required: true })], SignaturePolicyId.prototype, "SigPolicyId", void 0);
		__decorate([XmlChildElement({ parser: Transforms })], SignaturePolicyId.prototype, "Transforms", void 0);
		__decorate([XmlChildElement({ localName: XmlXades.ElementNames.SigPolicyHash, parser: SigPolicyHash, required: true })], SignaturePolicyId.prototype, "SigPolicyHash", void 0);
		__decorate([XmlChildElement({ localName: XmlXades.ElementNames.SigPolicyQualifiers, parser: SigPolicyQualifiers })], SignaturePolicyId.prototype, "SigPolicyQualifiers", void 0);
		SignaturePolicyId = __decorate([XmlElement({ localName: XmlXades.ElementNames.SignaturePolicyId })], SignaturePolicyId);
		return SignaturePolicyId;
	}(XadesObject);
	var XmlSignaturePolicyImpliedConverter = {
		set: function set(value) {
			return true;
		},
		get: function get(value) {
			return "";
		}
	};
	var SignaturePolicyIdentifier = function (_super) {
		__extends(SignaturePolicyIdentifier, _super);
		function SignaturePolicyIdentifier() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		__decorate([XmlChildElement({
			localName: XmlXades.ElementNames.SignaturePolicyId,
			namespaceURI: XmlXades.NamespaceURI,
			prefix: XmlXades.DefaultPrefix,
			parser: SignaturePolicyId
		})], SignaturePolicyIdentifier.prototype, "SignaturePolicyId", void 0);
		__decorate([XmlChildElement({
			localName: XmlXades.ElementNames.SignaturePolicyImplied,
			namespaceURI: XmlXades.NamespaceURI,
			prefix: XmlXades.DefaultPrefix,
			converter: XmlSignaturePolicyImpliedConverter,
			defaultValue: false
		})], SignaturePolicyIdentifier.prototype, "SignaturePolicyImplied", void 0);
		SignaturePolicyIdentifier = __decorate([XmlElement({ localName: XmlXades.ElementNames.SignaturePolicyIdentifier })], SignaturePolicyIdentifier);
		return SignaturePolicyIdentifier;
	}(XadesObject);

	var SignatureProductionPlace = function (_super) {
		__extends(SignatureProductionPlace, _super);
		function SignatureProductionPlace() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		__decorate([XmlChildElement({
			localName: XmlXades.ElementNames.City,
			defaultValue: "",
			namespaceURI: XmlXades.NamespaceURI,
			prefix: XmlXades.DefaultPrefix
		})], SignatureProductionPlace.prototype, "City", void 0);
		__decorate([XmlChildElement({
			localName: XmlXades.ElementNames.StateOrProvince,
			defaultValue: "",
			namespaceURI: XmlXades.NamespaceURI,
			prefix: XmlXades.DefaultPrefix
		})], SignatureProductionPlace.prototype, "StateOrProvince", void 0);
		__decorate([XmlChildElement({
			localName: XmlXades.ElementNames.PostalCode,
			defaultValue: "",
			namespaceURI: XmlXades.NamespaceURI,
			prefix: XmlXades.DefaultPrefix
		})], SignatureProductionPlace.prototype, "PostalCode", void 0);
		__decorate([XmlChildElement({
			localName: XmlXades.ElementNames.CountryName,
			defaultValue: "",
			namespaceURI: XmlXades.NamespaceURI,
			prefix: XmlXades.DefaultPrefix
		})], SignatureProductionPlace.prototype, "CountryName", void 0);
		SignatureProductionPlace = __decorate([XmlElement({ localName: XmlXades.ElementNames.SignatureProductionPlace })], SignatureProductionPlace);
		return SignatureProductionPlace;
	}(XadesObject);

	var ClaimedRole = function (_super) {
		__extends(ClaimedRole, _super);
		function ClaimedRole() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		ClaimedRole = __decorate([XmlElement({ localName: XmlXades.ElementNames.ClaimedRole })], ClaimedRole);
		return ClaimedRole;
	}(Any);
	var ClaimedRoles = function (_super) {
		__extends(ClaimedRoles, _super);
		function ClaimedRoles() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		ClaimedRoles = __decorate([XmlElement({ localName: XmlXades.ElementNames.ClaimedRoles, parser: ClaimedRole })], ClaimedRoles);
		return ClaimedRoles;
	}(XadesCollection);
	var CertifiedRole = function (_super) {
		__extends(CertifiedRole, _super);
		function CertifiedRole() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		CertifiedRole = __decorate([XmlElement({ localName: XmlXades.ElementNames.CertifiedRole })], CertifiedRole);
		return CertifiedRole;
	}(EncapsulatedPKIData);
	var CertifiedRoles = function (_super) {
		__extends(CertifiedRoles, _super);
		function CertifiedRoles() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		CertifiedRoles = __decorate([XmlElement({ localName: XmlXades.ElementNames.CertifiedRoles, parser: CertifiedRole })], CertifiedRoles);
		return CertifiedRoles;
	}(XadesCollection);
	var SignerRole = function (_super) {
		__extends(SignerRole, _super);
		function SignerRole() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		__decorate([XmlChildElement({ parser: ClaimedRoles })], SignerRole.prototype, "ClaimedRoles", void 0);
		__decorate([XmlChildElement({ parser: CertifiedRoles })], SignerRole.prototype, "CertifiedRoles", void 0);
		SignerRole = __decorate([XmlElement({ localName: XmlXades.ElementNames.SignerRole })], SignerRole);
		return SignerRole;
	}(XadesObject);

	var SignedSignatureProperties = function (_super) {
		__extends(SignedSignatureProperties, _super);
		function SignedSignatureProperties() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		__decorate([XmlAttribute({ localName: XmlXades.AttributeNames.Id, defaultValue: "" })], SignedSignatureProperties.prototype, "Id", void 0);
		__decorate([XmlChildElement({
			localName: XmlXades.ElementNames.SigningTime,
			parser: XadesDateTime
		})], SignedSignatureProperties.prototype, "SigningTime", void 0);
		__decorate([XmlChildElement({ parser: SigningCertificate })], SignedSignatureProperties.prototype, "SigningCertificate", void 0);
		__decorate([XmlChildElement({ parser: SignaturePolicyIdentifier })], SignedSignatureProperties.prototype, "SignaturePolicyIdentifier", void 0);
		__decorate([XmlChildElement({ parser: SignatureProductionPlace })], SignedSignatureProperties.prototype, "SignatureProductionPlace", void 0);
		__decorate([XmlChildElement({ parser: SignerRole })], SignedSignatureProperties.prototype, "SignerRole", void 0);
		SignedSignatureProperties = __decorate([XmlElement({
			localName: XmlXades.ElementNames.SignedSignatureProperties
		})], SignedSignatureProperties);
		return SignedSignatureProperties;
	}(XadesObject);

	var SignedProperties = function (_super) {
		__extends(SignedProperties, _super);
		function SignedProperties() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		__decorate([XmlAttribute({ localName: XmlXades.AttributeNames.Id, defaultValue: "" })], SignedProperties.prototype, "Id", void 0);
		__decorate([XmlChildElement({ parser: SignedSignatureProperties })], SignedProperties.prototype, "SignedSignatureProperties", void 0);
		__decorate([XmlChildElement({ parser: SignedDataObjectProperties })], SignedProperties.prototype, "SignedDataObjectProperties", void 0);
		SignedProperties = __decorate([XmlElement({
			localName: XmlXades.ElementNames.SignedProperties
		})], SignedProperties);
		return SignedProperties;
	}(XadesObject);

	var UnsignedDataObjectProperty = function (_super) {
		__extends(UnsignedDataObjectProperty, _super);
		function UnsignedDataObjectProperty() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		UnsignedDataObjectProperty = __decorate([XmlElement({ localName: XmlXades.ElementNames.UnsignedDataObjectProperty })], UnsignedDataObjectProperty);
		return UnsignedDataObjectProperty;
	}(Any);
	var UnsignedDataObjectProperties = function (_super) {
		__extends(UnsignedDataObjectProperties, _super);
		function UnsignedDataObjectProperties() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		__decorate([XmlAttribute({ localName: XmlXades.AttributeNames.Id, defaultValue: "" })], UnsignedDataObjectProperties.prototype, "Id", void 0);
		UnsignedDataObjectProperties = __decorate([XmlElement({ localName: XmlXades.ElementNames.UnsignedSignatureProperties, parser: UnsignedDataObjectProperty })], UnsignedDataObjectProperties);
		return UnsignedDataObjectProperties;
	}(XadesCollection);

	var OtherValue = function (_super) {
		__extends(OtherValue, _super);
		function OtherValue() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		OtherValue = __decorate([XmlElement({ localName: XmlXades.ElementNames.OtherValue })], OtherValue);
		return OtherValue;
	}(EncapsulatedPKIData);
	var OtherValues = function (_super) {
		__extends(OtherValues, _super);
		function OtherValues() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		OtherValues = __decorate([XmlElement({ localName: XmlXades.ElementNames.OCSPValues, parser: OtherValue })], OtherValues);
		return OtherValues;
	}(XadesCollection);
	var EncapsulatedOCSPValue = function (_super) {
		__extends(EncapsulatedOCSPValue, _super);
		function EncapsulatedOCSPValue() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		EncapsulatedOCSPValue = __decorate([XmlElement({ localName: XmlXades.ElementNames.EncapsulatedOCSPValue })], EncapsulatedOCSPValue);
		return EncapsulatedOCSPValue;
	}(EncapsulatedPKIData);
	var OCSPValues = function (_super) {
		__extends(OCSPValues, _super);
		function OCSPValues() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		OCSPValues = __decorate([XmlElement({ localName: XmlXades.ElementNames.OCSPValues })], OCSPValues);
		return OCSPValues;
	}(XadesCollection);
	var EncapsulatedCRLValue = function (_super) {
		__extends(EncapsulatedCRLValue, _super);
		function EncapsulatedCRLValue() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		EncapsulatedCRLValue = __decorate([XmlElement({ localName: XmlXades.ElementNames.EncapsulatedCRLValue })], EncapsulatedCRLValue);
		return EncapsulatedCRLValue;
	}(EncapsulatedPKIData);
	var CRLValues = function (_super) {
		__extends(CRLValues, _super);
		function CRLValues() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		CRLValues = __decorate([XmlElement({ localName: XmlXades.ElementNames.CRLValues, parser: EncapsulatedCRLValue })], CRLValues);
		return CRLValues;
	}(XadesCollection);
	var RevocationValues = function (_super) {
		__extends(RevocationValues, _super);
		function RevocationValues() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		__decorate([XmlAttribute({ localName: XmlXades.AttributeNames.Id, defaultValue: "" })], RevocationValues.prototype, "Id", void 0);
		__decorate([XmlChildElement({ parser: CRLValues })], RevocationValues.prototype, "CRLValues", void 0);
		__decorate([XmlChildElement({ parser: OCSPValues })], RevocationValues.prototype, "OCSPValues", void 0);
		__decorate([XmlChildElement({ parser: OtherValues })], RevocationValues.prototype, "OtherValues", void 0);
		RevocationValues = __decorate([XmlElement({ localName: XmlXades.ElementNames.RevocationValues })], RevocationValues);
		return RevocationValues;
	}(XadesObject);

	var SignatureTimeStamp = function (_super) {
		__extends(SignatureTimeStamp, _super);
		function SignatureTimeStamp() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		SignatureTimeStamp = __decorate([XmlElement({ localName: XmlXades.ElementNames.SignatureTimeStamp })], SignatureTimeStamp);
		return SignatureTimeStamp;
	}(XAdESTimeStamp);
	var SigAndRefsTimeStamp = function (_super) {
		__extends(SigAndRefsTimeStamp, _super);
		function SigAndRefsTimeStamp() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		SigAndRefsTimeStamp = __decorate([XmlElement({ localName: XmlXades.ElementNames.SigAndRefsTimeStamp })], SigAndRefsTimeStamp);
		return SigAndRefsTimeStamp;
	}(XAdESTimeStamp);
	var RefsOnlyTimeStamp = function (_super) {
		__extends(RefsOnlyTimeStamp, _super);
		function RefsOnlyTimeStamp() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		RefsOnlyTimeStamp = __decorate([XmlElement({ localName: XmlXades.ElementNames.RefsOnlyTimeStamp })], RefsOnlyTimeStamp);
		return RefsOnlyTimeStamp;
	}(XAdESTimeStamp);
	var ArchiveTimeStamp = function (_super) {
		__extends(ArchiveTimeStamp, _super);
		function ArchiveTimeStamp() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		ArchiveTimeStamp = __decorate([XmlElement({ localName: XmlXades.ElementNames.ArchiveTimeStamp })], ArchiveTimeStamp);
		return ArchiveTimeStamp;
	}(XAdESTimeStamp);
	var AttributeCertificateRefs = function (_super) {
		__extends(AttributeCertificateRefs, _super);
		function AttributeCertificateRefs() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		AttributeCertificateRefs = __decorate([XmlElement({ localName: XmlXades.ElementNames.AttributeCertificateRefs })], AttributeCertificateRefs);
		return AttributeCertificateRefs;
	}(CompleteCertificateRefs);
	var AttributeRevocationRefs = function (_super) {
		__extends(AttributeRevocationRefs, _super);
		function AttributeRevocationRefs() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		AttributeRevocationRefs = __decorate([XmlElement({ localName: XmlXades.ElementNames.AttributeRevocationRefs })], AttributeRevocationRefs);
		return AttributeRevocationRefs;
	}(CompleteRevocationRefs);
	var AttrAuthoritiesCertValues = function (_super) {
		__extends(AttrAuthoritiesCertValues, _super);
		function AttrAuthoritiesCertValues() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		AttrAuthoritiesCertValues = __decorate([XmlElement({ localName: XmlXades.ElementNames.AttrAuthoritiesCertValues })], AttrAuthoritiesCertValues);
		return AttrAuthoritiesCertValues;
	}(CertificateValues);
	var AttributeRevocationValues = function (_super) {
		__extends(AttributeRevocationValues, _super);
		function AttributeRevocationValues() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		AttributeRevocationValues = __decorate([XmlElement({ localName: XmlXades.ElementNames.AttributeRevocationValues })], AttributeRevocationValues);
		return AttributeRevocationValues;
	}(RevocationValues);

	var UnsignedSignatureProperty = function (_super) {
		__extends(UnsignedSignatureProperty, _super);
		function UnsignedSignatureProperty() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		UnsignedSignatureProperty = __decorate([XmlElement({ localName: "UnsignedSignatureProperty" })], UnsignedSignatureProperty);
		return UnsignedSignatureProperty;
	}(XadesObject);
	var UnsignedSignatureProperties = function (_super) {
		__extends(UnsignedSignatureProperties, _super);
		function UnsignedSignatureProperties() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		UnsignedSignatureProperties.prototype.OnLoadXml = function (element) {
			for (var i = 0; i < element.childNodes.length; i++) {
				var node = element.childNodes.item(i);
				if (node.nodeType !== XmlNodeType.Element) {
					continue;
				}
				var XmlClass = void 0;
				switch (node.localName) {
					case XmlXades.ElementNames.CounterSignature:
						XmlClass = CounterSignature;
						break;
					case XmlXades.ElementNames.SignatureTimeStamp:
						XmlClass = SignatureTimeStamp;
						break;
					case XmlXades.ElementNames.CompleteCertificateRefs:
						XmlClass = CompleteCertificateRefs;
						break;
					case XmlXades.ElementNames.CompleteRevocationRefs:
						XmlClass = CompleteRevocationRefs;
						break;
					case XmlXades.ElementNames.AttributeCertificateRefs:
						XmlClass = AttributeCertificateRefs;
						break;
					case XmlXades.ElementNames.AttributeRevocationRefs:
						XmlClass = AttributeRevocationRefs;
						break;
					case XmlXades.ElementNames.SigAndRefsTimeStamp:
						XmlClass = SigAndRefsTimeStamp;
						break;
					case XmlXades.ElementNames.RefsOnlyTimeStamp:
						XmlClass = RefsOnlyTimeStamp;
						break;
					case XmlXades.ElementNames.CertificateValues:
						XmlClass = CertificateValues;
						break;
					case XmlXades.ElementNames.RevocationValues:
						XmlClass = RevocationValues;
						break;
					case XmlXades.ElementNames.AttrAuthoritiesCertValues:
						XmlClass = AttrAuthoritiesCertValues;
						break;
					case XmlXades.ElementNames.AttributeRevocationValues:
						XmlClass = AttributeRevocationValues;
						break;
					case XmlXades.ElementNames.ArchiveTimeStamp:
						XmlClass = ArchiveTimeStamp;
						break;
				}
				if (XmlClass) {
					var item = XmlClass.LoadXml(node);
					this.Add(item);
				}
			}
		};
		__decorate([XmlAttribute({ localName: XmlXades.AttributeNames.Id, defaultValue: "" })], UnsignedSignatureProperties.prototype, "Id", void 0);
		UnsignedSignatureProperties = __decorate([XmlElement({ localName: XmlXades.ElementNames.UnsignedSignatureProperties, parser: UnsignedSignatureProperty })], UnsignedSignatureProperties);
		return UnsignedSignatureProperties;
	}(XadesCollection);

	var UnsignedProperties = function (_super) {
		__extends(UnsignedProperties, _super);
		function UnsignedProperties() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		__decorate([XmlAttribute({ localName: XmlXades.AttributeNames.Id, defaultValue: "" })], UnsignedProperties.prototype, "Id", void 0);
		__decorate([XmlChildElement({ parser: UnsignedSignatureProperties })], UnsignedProperties.prototype, "UnsignedSignatureProperties", void 0);
		__decorate([XmlChildElement({ parser: UnsignedDataObjectProperties })], UnsignedProperties.prototype, "UnsignedDataObjectProperties", void 0);
		UnsignedProperties = __decorate([XmlElement({
			localName: XmlXades.ElementNames.UnsignedProperties
		})], UnsignedProperties);
		return UnsignedProperties;
	}(XadesObject);

	var QualifyingProperties = function (_super) {
		__extends(QualifyingProperties, _super);
		function QualifyingProperties() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		__decorate([XmlAttribute({ localName: XmlXades.AttributeNames.Target, required: true })], QualifyingProperties.prototype, "Target", void 0);
		__decorate([XmlAttribute({ localName: XmlXades.AttributeNames.Id, defaultValue: "" })], QualifyingProperties.prototype, "Id", void 0);
		__decorate([XmlChildElement({ parser: SignedProperties })], QualifyingProperties.prototype, "SignedProperties", void 0);
		__decorate([XmlChildElement({ parser: UnsignedProperties })], QualifyingProperties.prototype, "UnsignedProperties", void 0);
		QualifyingProperties = __decorate([XmlElement({
			localName: XmlXades.ElementNames.QualifyingProperties
		})], QualifyingProperties);
		return QualifyingProperties;
	}(XadesObject);

	var QualifyingPropertiesReference = function (_super) {
		__extends(QualifyingPropertiesReference, _super);
		function QualifyingPropertiesReference() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		__decorate([XmlAttribute({ localName: XmlXades.AttributeNames.URI, required: true })], QualifyingPropertiesReference.prototype, "Uri", void 0);
		__decorate([XmlAttribute({ localName: XmlXades.AttributeNames.Id, defaultValue: "" })], QualifyingPropertiesReference.prototype, "Id", void 0);
		QualifyingPropertiesReference = __decorate([XmlElement({ localName: XmlXades.ElementNames.QualifyingPropertiesReference })], QualifyingPropertiesReference);
		return QualifyingPropertiesReference;
	}(XadesObject);

	var DataObject$1 = function (_super) {
		__extends(DataObject$$1, _super);
		function DataObject$$1() {
			return _super !== null && _super.apply(this, arguments) || this;
		}
		__decorate([XmlChildElement({ parser: QualifyingProperties })], DataObject$$1.prototype, "QualifyingProperties", void 0);
		DataObject$$1 = __decorate([XmlElement({ localName: XmlSignature.ElementNames.Object })], DataObject$$1);
		return DataObject$$1;
	}(DataObject);

	var xadesXml = Object.freeze({
		Any: Any,
		AnyCollection: AnyCollection,
		OtherCertificate: OtherCertificate,
		OtherCertificateCollection: OtherCertificateCollection,
		EncapsulatedX509Certificate: EncapsulatedX509Certificate,
		EncapsulatedX509CertificateCollection: EncapsulatedX509CertificateCollection,
		CertificateValues: CertificateValues,
		CommitmentTypeQualifier: CommitmentTypeQualifier,
		CommitmentTypeQualifiers: CommitmentTypeQualifiers,
		ObjectReference: ObjectReference,
		ObjectReferenceCollection: ObjectReferenceCollection,
		CommitmentTypeIndication: CommitmentTypeIndication,
		CompleteCertificateRefs: CompleteCertificateRefs,
		OtherRef: OtherRef,
		OtherRefs: OtherRefs,
		ResponderID: ResponderID,
		OCSPIdentifier: OCSPIdentifier,
		OCSPRef: OCSPRef,
		OCSPRefs: OCSPRefs,
		CRLIdentifier: CRLIdentifier,
		CRLRef: CRLRef,
		CRLRefs: CRLRefs,
		CompleteRevocationRefs: CompleteRevocationRefs,
		CounterSignature: CounterSignature,
		DataObjectFormat: DataObjectFormat,
		XadesDateTime: XadesDateTime,
		EncapsulatedPKIData: EncapsulatedPKIData,
		Include: Include,
		ReferenceInfo: ReferenceInfo,
		ReferenceInfos: ReferenceInfos,
		EncapsulatedTimeStamp: EncapsulatedTimeStamp,
		EncapsulatedTimeStampCollection: EncapsulatedTimeStampCollection,
		XMLTimeStamp: XMLTimeStamp,
		XMLTimeStampCollection: XMLTimeStampCollection,
		GenericTimeStamp: GenericTimeStamp,
		Identifier: Identifier,
		DocumentationReference: DocumentationReference,
		DocumentationReferences: DocumentationReferences,
		ObjectIdentifier: ObjectIdentifier,
		OtherTimeStamp: OtherTimeStamp,
		QualifyingProperties: QualifyingProperties,
		QualifyingPropertiesReference: QualifyingPropertiesReference,
		OtherValue: OtherValue,
		OtherValues: OtherValues,
		EncapsulatedOCSPValue: EncapsulatedOCSPValue,
		OCSPValues: OCSPValues,
		EncapsulatedCRLValue: EncapsulatedCRLValue,
		CRLValues: CRLValues,
		RevocationValues: RevocationValues,
		SigPolicyId: SigPolicyId,
		SigPolicyHash: SigPolicyHash,
		SigPolicyQualifier: SigPolicyQualifier,
		Integer: Integer$1,
		IntegerList: IntegerList,
		NoticeReference: NoticeReference,
		SPUserNotice: SPUserNotice,
		SPURI: SPURI,
		SigPolicyQualifiers: SigPolicyQualifiers,
		SignaturePolicyId: SignaturePolicyId,
		SignaturePolicyIdentifier: SignaturePolicyIdentifier,
		SignatureProductionPlace: SignatureProductionPlace,
		IndividualDataObjectsTimeStamp: IndividualDataObjectsTimeStamp,
		IndividualDataObjectsTimeStampCollection: IndividualDataObjectsTimeStampCollection,
		AllDataObjectsTimeStamp: AllDataObjectsTimeStamp,
		DataObjectFormatCollection: DataObjectFormatCollection,
		CommitmentTypeIndicationCollection: CommitmentTypeIndicationCollection,
		AllDataObjectsTimeStampCollection: AllDataObjectsTimeStampCollection,
		SignedDataObjectProperties: SignedDataObjectProperties,
		SignedProperties: SignedProperties,
		SignedSignatureProperties: SignedSignatureProperties,
		ClaimedRole: ClaimedRole,
		ClaimedRoles: ClaimedRoles,
		CertifiedRole: CertifiedRole,
		CertifiedRoles: CertifiedRoles,
		SignerRole: SignerRole,
		DigestAlgAndValueType: DigestAlgAndValueType,
		IssuerSerial: IssuerSerial,
		Cert: Cert,
		CertIDList: CertIDList,
		SigningCertificate: SigningCertificate,
		UnsignedDataObjectProperty: UnsignedDataObjectProperty,
		UnsignedDataObjectProperties: UnsignedDataObjectProperties,
		UnsignedProperties: UnsignedProperties,
		SignatureTimeStamp: SignatureTimeStamp,
		SigAndRefsTimeStamp: SigAndRefsTimeStamp,
		RefsOnlyTimeStamp: RefsOnlyTimeStamp,
		ArchiveTimeStamp: ArchiveTimeStamp,
		AttributeCertificateRefs: AttributeCertificateRefs,
		AttributeRevocationRefs: AttributeRevocationRefs,
		AttrAuthoritiesCertValues: AttrAuthoritiesCertValues,
		AttributeRevocationValues: AttributeRevocationValues,
		UnsignedSignatureProperty: UnsignedSignatureProperty,
		UnsignedSignatureProperties: UnsignedSignatureProperties,
		XAdESTimeStamp: XAdESTimeStamp,
		DataObject: DataObject$1,
		XmlXades: XmlXades
	});

	var XADES_REFERENCE_TYPE = "http://uri.etsi.org/01903#SignedProperties";
	var SignedXml$1 = function (_super) {
		__extends(SignedXml$$1, _super);
		function SignedXml$$1(node) {
			var _this = _super.call(this, node) || this;
			_this.properties = null;
			_this.CreateQualifyingProperties();
			return _this;
		}
		Object.defineProperty(SignedXml$$1.prototype, "Properties", {
			get: function get() {
				return this.properties;
			},
			enumerable: true,
			configurable: true
		});
		Object.defineProperty(SignedXml$$1.prototype, "SignedProperties", {
			get: function get() {
				if (!this.Properties) {
					throw new XmlError(XE.XML_EXCEPTION, "Properties is empty");
				}
				return this.Properties.SignedProperties;
			},
			enumerable: true,
			configurable: true
		});
		Object.defineProperty(SignedXml$$1.prototype, "UnsignedProperties", {
			get: function get() {
				if (!this.Properties) {
					throw new XmlError(XE.XML_EXCEPTION, "Properties is empty");
				}
				return this.Properties.UnsignedProperties;
			},
			enumerable: true,
			configurable: true
		});

		SignedXml$$1.prototype.LoadXml = function (value, useContainer) {
			_super.prototype.LoadXml.call(this, value);
			var properties = null;
			this.XmlSignature.ObjectList.Some(function (item) {
				if (item.Element) {
					for (var i = 0; i < item.Element.childNodes.length; i++) {
						var node = item.Element.childNodes.item(i);
						if (node.nodeType === XmlNodeType.Element && node.localName === XmlXades.ElementNames.QualifyingProperties) {
							properties = QualifyingProperties.LoadXml(node);
							return true;
						}
					}
				}
				return false;
			});
			this.properties = properties;
		};
		SignedXml$$1.prototype.Sign = function (algorithm, key, data, options) {
			return (_a = _super.prototype.Sign).apply.call(_a, this, arguments);
			var _a;
		};

		SignedXml$$1.prototype.CreateQualifyingProperties = function () {
			if (this.Properties) {
				throw new XmlError(XE.XML_EXCEPTION, "Cannot create QualifyingProperties cause current signature has got one. You must create CounterSignature");
			}
			var rnd = Application.crypto.getRandomValues(new Uint8Array(6));
			var id = Convert.ToHex(rnd);
			this.XmlSignature.Id = "id-" + id;
			var dataObject = new DataObject$1();
			dataObject.QualifyingProperties.Target = "#" + this.XmlSignature.Id;
			dataObject.QualifyingProperties.SignedProperties.Id = "xades-" + this.XmlSignature.Id;
			this.properties = dataObject.QualifyingProperties;
			this.XmlSignature.ObjectList.Add(dataObject);
		};
		SignedXml$$1.prototype.ApplySignOptions = function (signature, algorithm, key, options) {
			return __awaiter(this, void 0, void 0, function () {
				var sigProps, signingAlg, xadesRefHash, xadesRef;
				return __generator(this, function (_a) {
					switch (_a.label) {
						case 0:
							return [4, _super.prototype.ApplySignOptions.call(this, signature, algorithm, key, options)];
						case 1:
							_a.sent();
							if (!this.Properties) return [3, 4];
							sigProps = this.Properties.SignedProperties.SignedSignatureProperties;
							sigProps.SigningTime.Value = new Date();
							signingAlg = assign$1({}, algorithm, key.algorithm);
							xadesRefHash = signingAlg.hash;
							xadesRef = new Reference();
							xadesRef.Type = XADES_REFERENCE_TYPE;
							xadesRef.Uri = "#" + this.Properties.SignedProperties.Id;
							xadesRef.DigestMethod.Algorithm = CryptoConfig.GetHashAlgorithm(xadesRefHash).namespaceURI;
							signature.SignedInfo.References.Add(xadesRef);
							return [4, this.ApplySigningCertificate(options.signingCertificate)];
						case 2:
							_a.sent();
							return [4, this.ApplySignaturePolicyIdentifier(options.policy)];
						case 3:
							_a.sent();
							this.ApplySignatureProductionPlace(options.productionPlace);
							this.ApplySignerRoles(options.signerRole);
							_a.label = 4;
						case 4:
							return [2];
					}
				});
			});
		};
		SignedXml$$1.prototype.ApplySigningCertificate = function (base64string) {
			return __awaiter(this, void 0, void 0, function () {
				var raw, cert, ssp, signingCertificate, alg, _a, _b;
				return __generator(this, function (_c) {
					switch (_c.label) {
						case 0:
							if (!(this.Properties && base64string)) return [3, 2];
							raw = Convert.FromBase64(base64string);
							cert = new X509Certificate(raw);
							ssp = this.Properties.SignedProperties.SignedSignatureProperties;
							if (ssp.SigningCertificate.Count) {
								throw new XmlError(XE.XML_EXCEPTION, "Signature can contain only one SigningCertificate");
							}
							signingCertificate = new Cert();
							signingCertificate.IssuerSerial.X509IssuerName = cert.Issuer;
							signingCertificate.IssuerSerial.X509SerialNumber = cert.SerialNumber;
							alg = CryptoConfig.GetHashAlgorithm("SHA-256");
							signingCertificate.CertDigest.DigestMethod.Algorithm = alg.namespaceURI;
							_a = signingCertificate.CertDigest;
							_b = Uint8Array.bind;
							return [4, cert.Thumbprint(alg.algorithm.name)];
						case 1:
							_a.DigestValue = new (_b.apply(Uint8Array, [void 0, _c.sent()]))();
							this.Properties.SignedProperties.SignedSignatureProperties.SigningCertificate.Add(signingCertificate);
							_c.label = 2;
						case 2:
							return [2];
					}
				});
			});
		};
		SignedXml$$1.prototype.ApplySignaturePolicyIdentifier = function (options) {
			return __awaiter(this, void 0, void 0, function () {
				var _this = this;
				var ssp, policyId_1, digestAlgorithm, identifierDoc, identifierContent, c14n, _a;
				return __generator(this, function (_b) {
					switch (_b.label) {
						case 0:
							if (!this.Properties) return [3, 3];
							ssp = this.Properties.SignedProperties.SignedSignatureProperties;
							if (!(options && (typeof options === 'undefined' ? 'undefined' : _typeof(options)) === "object")) return [3, 2];
							policyId_1 = new SignaturePolicyId();
							policyId_1.SigPolicyId = new SigPolicyId();
							policyId_1.SigPolicyId.Identifier = new Identifier();
							policyId_1.SigPolicyId.Identifier.Qualifier = options.identifier.qualifier;
							policyId_1.SigPolicyId.Identifier.Value = options.identifier.value;
							if (options.identifier.description) {
								policyId_1.SigPolicyId.Description = options.identifier.description;
							}
							if (options.identifier.references) {
								policyId_1.SigPolicyId.DocumentationReferences = new DocumentationReferences();
								options.identifier.references.forEach(function (referenceValue) {
									var reference = new DocumentationReference();
									reference.Uri = referenceValue;
									policyId_1.SigPolicyId.DocumentationReferences.Add(reference);
								});
							}
							if (options.transforms && options.transforms.length) {
								policyId_1.Transforms = new Transforms();
								options.transforms.forEach(function (transform) {
									policyId_1.Transforms.Add(_this.ResolveTransform(transform));
								});
							}
							policyId_1.SigPolicyHash = new SigPolicyHash();
							policyId_1.SigPolicyHash.DigestMethod = new DigestMethod();
							digestAlgorithm = CryptoConfig.GetHashAlgorithm(options.hash);
							policyId_1.SigPolicyHash.DigestMethod.Algorithm = digestAlgorithm.namespaceURI;
							identifierDoc = policyId_1.SigPolicyId.Identifier.GetXml().cloneNode(true);
							this.CopyNamespaces(identifierDoc, identifierDoc, true);
							this.InjectNamespaces(this.GetSignatureNamespaces(), identifierDoc, true);
							identifierContent = null;
							if (policyId_1.Transforms && policyId_1.Transforms.Count) {
								identifierContent = this.ApplyTransforms(policyId_1.Transforms, identifierDoc);
							} else {
								c14n = new XmlDsigC14NTransform();
								c14n.LoadInnerXml(identifierDoc);
								identifierContent = c14n.GetOutput();
							}
							_a = policyId_1.SigPolicyHash;
							return [4, digestAlgorithm.Digest(identifierContent)];
						case 1:
							_a.DigestValue = _b.sent();
							if (options.qualifiers) {
								policyId_1.SigPolicyQualifiers = new SigPolicyQualifiers();
								options.qualifiers.forEach(function (qualifierValue) {
									var container = new SigPolicyQualifier();
									if (typeof qualifierValue === "string") {
										var qualifier = new SPURI();
										qualifier.Value = qualifierValue;
										container.Add(qualifier);
									} else {
										var qualifier_1 = new SPUserNotice();
										if (qualifierValue.explicitText) {
											qualifier_1.ExplicitText = qualifierValue.explicitText;
										}
										if (qualifierValue.noticeRef) {
											qualifier_1.NoticeRef = new NoticeReference();
											qualifier_1.NoticeRef.Organization = qualifierValue.noticeRef.organization;
											qualifier_1.NoticeRef.NoticeNumbers = new IntegerList();
											if (qualifierValue.noticeRef.noticeNumbers) {
												qualifierValue.noticeRef.noticeNumbers.forEach(function (numberValue) {
													var noticeNumber = new Integer$1();
													noticeNumber.Value = numberValue;
													qualifier_1.NoticeRef.NoticeNumbers.Add(noticeNumber);
												});
											}
										}
										container.Add(qualifier_1);
									}
									policyId_1.SigPolicyQualifiers.Add(container);
								});
							}
							ssp.SignaturePolicyIdentifier.SignaturePolicyId = policyId_1;
							ssp.SignaturePolicyIdentifier.SignaturePolicyImplied = false;
							return [3, 3];
						case 2:
							ssp.SignaturePolicyIdentifier.SignaturePolicyImplied = true;
							_b.label = 3;
						case 3:
							return [2];
					}
				});
			});
		};
		SignedXml$$1.prototype.ApplySignatureProductionPlace = function (options) {
			if (this.Properties && options) {
				var ssp = this.Properties.SignedProperties.SignedSignatureProperties;
				if (options.city) {
					ssp.SignatureProductionPlace.City = options.city;
				}
				if (options.code) {
					ssp.SignatureProductionPlace.PostalCode = options.code;
				}
				if (options.country) {
					ssp.SignatureProductionPlace.CountryName = options.country;
				}
				if (options.state) {
					ssp.SignatureProductionPlace.StateOrProvince = options.state;
				}
			}
		};
		SignedXml$$1.prototype.ApplySignerRoles = function (options) {
			if (this.Properties && options) {
				var ssp_1 = this.Properties.SignedProperties.SignedSignatureProperties;
				if (options.claimed) {
					options.claimed.forEach(function (role) {
						var claimedRole = new ClaimedRole();
						claimedRole.Value = role;
						ssp_1.SignerRole.ClaimedRoles.Add(claimedRole);
					});
				}
				if (options.certified) {
					options.certified.forEach(function (role) {
						var certifiedRole = new CertifiedRole();
						certifiedRole.Encoding = "der";
						certifiedRole.Value = Convert.FromBase64(role);
						ssp_1.SignerRole.CertifiedRoles.Add(certifiedRole);
					});
				}
			}
		};
		SignedXml$$1.prototype.VerifySigningCertificate = function () {
			return __awaiter(this, void 0, void 0, function () {
				var x509, ssp, alg, signingCertificate, b64CertDigest, keyInfos, i, item, certs, j, cert, hash, _a, b64Hash;
				return __generator(this, function (_b) {
					switch (_b.label) {
						case 0:
							x509 = null;
							if (!(this.XmlSignature && this.Properties)) return [3, 7];
							ssp = this.Properties.SignedProperties.SignedSignatureProperties;
							if (ssp.SigningCertificate.Count !== 1) {
								throw new XmlError(XE.XML_EXCEPTION, "Signature has got wrong amount of SigningCertificate, MUST be one");
							}
							alg = CryptoConfig.GetHashAlgorithm("SHA-256");
							signingCertificate = ssp.SigningCertificate.Item(0);
							b64CertDigest = Convert.ToBase64(signingCertificate.CertDigest.DigestValue);
							keyInfos = this.XmlSignature.KeyInfo;
							i = 0;
							_b.label = 1;
						case 1:
							if (!(i < keyInfos.Count, !x509)) return [3, 6];
							item = keyInfos.Item(i);
							if (!(item instanceof KeyInfoX509Data)) return [3, 5];
							certs = item.Certificates;
							j = 0;
							_b.label = 2;
						case 2:
							if (!(j < certs.length, !x509)) return [3, 5];
							cert = certs[j];
							if (!cert) {
								return [3, 4];
							}
							_a = Uint8Array.bind;
							return [4, cert.Thumbprint(alg.algorithm)];
						case 3:
							hash = new (_a.apply(Uint8Array, [void 0, _b.sent()]))();
							b64Hash = Convert.ToBase64(hash);
							if (b64Hash === b64CertDigest) {
								x509 = cert;
							}
							_b.label = 4;
						case 4:
							j++;
							return [3, 2];
						case 5:
							i++;
							return [3, 1];
						case 6:
							if (!(x509 && x509.Issuer === signingCertificate.IssuerSerial.X509IssuerName && x509.SerialNumber === signingCertificate.IssuerSerial.X509SerialNumber)) {
								throw new XmlError(XE.XML_EXCEPTION, "SigningCertificate not found");
							}
							_b.label = 7;
						case 7:
							return [2, x509];
					}
				});
			});
		};
		return SignedXml$$1;
	}(SignedXml);

	var xml = xadesXml;

	exports.xml = xml;
	exports.Application = Application;
	exports.Select = Select;
	exports.Parse = Parse;
	exports.Convert = Convert;
	exports.SignedXml = SignedXml$1;

	Object.defineProperty(exports, '__esModule', { value: true });
});
